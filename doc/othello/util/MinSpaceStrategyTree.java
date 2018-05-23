package othello.util;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import othello.model.Board;
import othello.model.IBoard;

public class MinSpaceStrategyTree implements StrategyTree {
	private static final int BITS = 
		(int) Math.ceil(Math.log((Color.values().length + 1)) / Math.log(2.0));
	private static final long BIT_MASK = (long) Math.pow(2, BITS) - 1;
	
	private final IBoard board;
	private final MSNode root;
	
	public class MSNode implements Node {
		private final List<Node> children;
		private final long[] boardState;
		private double evaluation;
		
		// CONSTRUCTEURS
		public MSNode(MSNode parent, Coord move, Color c, double ev) {
			if (parent == null || move == null || c == null) {
				throw new AssertionError();
			}
			children = new LinkedList<Node>();
			evaluation = ev;
			boardState = parent.boardState.clone();
			setDisk(move, c);
		}
		
		public MSNode(IBoard b) {
			if (b == null) {
				throw new AssertionError();
			}
			children = new LinkedList<Node>();
			evaluation = Double.NEGATIVE_INFINITY;
			boardState = new long[(b.getSize()*b.getSize()*BITS) / Long.SIZE];
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

		public Color getDisk(Coord move) {
			assert(move != null);
			assert(move.isInRect(new Coord(board.getSize(), board.getSize())));
			
			long a = boardState[shift(move) / Long.SIZE] >> (shift(move) % Long.SIZE);
			return a == 0 ? null :
				Color.values()[(int) (a & BIT_MASK)];
		}

		// COMMANDES
		public void setEval(double e) {
			evaluation = e;
		}
		
		public void setDisk(Coord move, Color c) {
			
		}
		
		// OUTILS
		private void updateCell(Coord move, Color c) {
			assert(move != null && c != null);
			assert(move.isInRect(new Coord(board.getSize(), board.getSize())));
			
			long a = (c.ordinal() + 1) << (shift(move) % Long.SIZE);
			boardState[shift(move) / Long.SIZE] |= a;
		}
		
		private int shift(Coord c) {
			return BITS * c.row() * board.getSize() + c.col();
		}
		
	}
	
	// CONSTRUCTEURS
	public MinSpaceStrategyTree(IBoard board) {
		this.board = board;
		this.root = new MSNode(board);
	}
	
	// REQUETES
	public IBoard getBoard() {
		return board;
	}
	
	public Node getRoot() {
		return root;
	}
	
	public static void main(String[] args) {
		IBoard b = new Board(8);
		MinSpaceStrategyTree t = new MinSpaceStrategyTree(b);
		Node m = t.getRoot();
		
		for (int k = 0; k < 8; k++) {
			for (int j = 0; j < 8; k++) {
				System.out.print(m.getDisk(new Coord(k, j)) + " ");
			}
			System.out.println("");
		}
	}
	
}
