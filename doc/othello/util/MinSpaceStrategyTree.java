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
		private final long[] boardState; // représentation compact du plateau
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
			boardState = boardToLong(b);
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
			
			long a = (boardState[shift(move) / Long.SIZE] >> (shift(move) % Long.SIZE))
					& BIT_MASK;
			return a == 0 ? null :
				Color.values()[(int) a - 1];
		}
		
		public String toString() {
			String s = "eval=" + evaluation + ":" + System.lineSeparator();
			for (int row = 0; row < 8; row++) {
				for (int col = 0; col < 8; col++) {
					s += getDisk(new Coord(row, col)) + " ";
				}
				s += System.lineSeparator();
			}
			return s;
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
		b.putDisk(new Coord(0,0), Color.BLACK);
		b.putDisk(new Coord(4,6), Color.WHITE);
		
		MinSpaceStrategyTree t = new MinSpaceStrategyTree(b);
		Node m = t.getRoot();
		
		System.out.println(m);
	}
	
}
