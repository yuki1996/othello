package othello.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import othello.model.AbstractBoard;
import othello.model.Board;
import othello.model.IBoard;

public class MinSpaceStrategyTree implements StrategyTree {
	private static final int BITS = 
		(int) Math.ceil(Math.log((Color.values().length + 1)) / Math.log(2.0));
	private static final long BIT_MASK = (long) Math.pow(2, BITS) - 1;
	
	private final IBoard board;
	private final int maxDepth;
	private Node root;
	
	public class MSNode extends AbstractBoard implements Node {
		private final Color playerColor;
		private final long[] boardState; // représentation compact du plateau
		private final Node parent;
		private final int depth;
		private final Coord move;
		private double evaluation;
		private List<Node> children; // null => enfants non générés
		
		// CONSTRUCTEURS
		public MSNode(MSNode parent, Coord move, double ev) {
			super(board.getSize());
			if (parent == null) {
				throw new AssertionError();
			}
			playerColor = Color.values()[(parent.playerColor.ordinal() + 1) % Color.values().length];
			children = null;
			evaluation = ev;
			boardState = parent.boardState.clone();
			this.parent = parent;
			depth = parent.depth + 1;
			this.move = move;
			if (move != null) {
				playAShot(move, parent.playerColor);
			}
		}
		
		public MSNode(IBoard b, Color c) {
			super(board.getSize());
			if (b == null) {
				throw new AssertionError();
			}
			playerColor = c;
			children = null;
			evaluation = Double.POSITIVE_INFINITY;
			boardState = boardToLong(b);
			parent = null;
			depth = 0;
			move = null;
			generateNextLevel(0);
		}
		
		// REQUETE
		// discutable
		public Iterator<Node> iterator() {
			return children.iterator();
		}
		
		public List<? extends Node> children() {
			return children;
		}
		
		public double getEval() {
			return evaluation;
		}
		
		public Color getPlayerColor() {
			return playerColor;
		}

		public Node getParent() {
			return parent;
		}
		
		public int getDepth() {
			return depth;
		}
		
		public Coord getMove() {
			return move;
		}
		
		public List<Color> getAllDisks() {
			List<Color> res = new LinkedList<Color>();
			int size = board.getSize();
			
			for (int row = 0; row < size; row++) {
				for (int col = 0; col < size; col++) {
					res.add(getColor(new Coord(row, col)));
				}
			}
			return res;
		}
		
		public Color getColor(Coord move) {
			assert(move != null);
			assert(move.isInRect(new Coord(board.getSize(), board.getSize())));
			
			long a = (boardState[shift(move) / Long.SIZE] >> (shift(move) % Long.SIZE))
					& BIT_MASK;
			return a == 0 ? null :
				Color.values()[(int) a - 1];
		}
		
		public String toString() {
			String s = playerColor + ":coup=" + move + ":eval=" + evaluation + ":" + System.lineSeparator();
			s += super.toString();
			return s;
		}
		
		public boolean equals(Object obj) {
			if (obj != null && this.getClass() == obj.getClass()) {
				MSNode c = (MSNode) obj;
				return evaluation == c.evaluation && boardState.equals(c.boardState);		// A DEVELOPPER
			}
			return false;
		}
		
		public Set<Coord> getDisksOfPlayer(Color playerColor) {
			int size = board.getSize();
			Set<Coord> res = new HashSet<Coord>();
			
			for (int row = 0; row < size; row++) {
				for (int col = 0; col < size; col++) {
					Coord coord = new Coord(row, col);
					if (getColor(coord) == playerColor) {
						res.add(coord);
					}
				}
			}
			return res;
		}

		// COMMANDES
		public void setEval(double e) {
			evaluation = e;
		}
		
		public void putDisk(Coord move, Color c) {
			assert(move != null && c != null);
			assert(move.isInRect(new Coord(board.getSize(), board.getSize())));

			long a = ((long) (c.ordinal() + 1)) << (shift(move) % Long.SIZE);
			long b = ((long) BIT_MASK) << (shift(move) % Long.SIZE);
			boardState[shift(move) / Long.SIZE] &= ~b;
			boardState[shift(move) / Long.SIZE] |= a;
		}
		
		public void generateChildren() {
			children = new ArrayList<Node>();
			Set<Coord> movesOfPlayer = getValidMoves(playerColor);

			if (movesOfPlayer.isEmpty()) {
				if (!getValidMoves(Color.values()[(playerColor.ordinal() + 1) % Color.values().length]).isEmpty()) {
					children.add(new MSNode(this, null, evaluation));
				}
			} else {
				for (Coord d : movesOfPlayer) {
	                children.add(new MSNode(this, d, evaluation));
			    }
			}
		}
		
		public void generateNextLevel(int rootLevel) {
			
			if (children != null && !children.isEmpty()) {
				for (Node child : children) {
					child.generateNextLevel(rootLevel);
				}
			}
			else if (children == null && depth - rootLevel <= maxDepth) {
				generateChildren();
				generateNextLevel(rootLevel);
			} else if (children == null) {
				generateChildren();
			}
		}
		
		// OUTILS
		private int shift(Coord c) {
			return BITS * (c.row() * board.getSize() + c.col());
		}
		
		/**
		 * Conversion d'un IBoard vers un long[] (représentation compact).
		 */
		private long[] boardToLong(IBoard board) {
			assert(board != null);
			
			int size = board.getSize();
			long[] res = new long[(size*size*BITS) / Long.SIZE];
			
			for (int row = size - 1; row >= 0; row--) {
				for (int col = size - 1; col >= 0; col--) {
					Coord coord = new Coord(row, col);
					Color c = board.getColor(coord);
					int ind = shift(coord) / Long.SIZE;
					res[ind] <<= BITS;
					res[ind] |= c == null ? 0 : (c.ordinal() + 1);
				}
			}
			return res;
		}
		
	}
	
	// CONSTRUCTEURS
	public MinSpaceStrategyTree(IBoard board, int maxDepth) {
		this.board = board;
		this.root = new MSNode(board, Color.BLACK);
		this.maxDepth = maxDepth;
	}
	
	// REQUETES
	public IBoard getBoard() {
		return board;
	}
	
	public Node getRoot() {
		return root;
	}
	
	public void move(Coord c) {
		for (Node n : root.children()) {
			if (n.getMove() == null && c == null || n.getMove().equals(c)) {
				root = n;
				root.generateNextLevel(maxDepth);
			}
		}
	}

	public static void main(String[] args) {
		IBoard b = new Board(8);
		b.putDisk(new Coord(3,3), Color.BLACK);
		b.putDisk(new Coord(3,4), Color.WHITE);
		b.putDisk(new Coord(4,3), Color.WHITE);
		b.putDisk(new Coord(4,4), Color.BLACK);
		
		MinSpaceStrategyTree t = new MinSpaceStrategyTree(b, 4);
		
		
		System.out.println(t.getRoot());
		System.out.println(t.getRoot().children().size());
		t.move(new Coord(5,3));
		System.out.println(t.getRoot());
		System.out.println(t.getRoot().children().size());
		
		System.out.println(t.getRoot().children());
		
//		t.move(new Coord(3,2));
//		System.out.println(t.getRoot());
//		System.out.println(t.getRoot().children().size());
//		t.move(new Coord(3,1));
//		System.out.println(t.getRoot());
//		System.out.println(t.getRoot().children().size());
	}
	
}
