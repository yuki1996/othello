package othello.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import othello.model.Board;
import othello.model.IBoard;

public class MinSpaceStrategyTree implements StrategyTree {
	private static final int BITS = 
		(int) Math.ceil(Math.log((Color.values().length + 1)) / Math.log(2.0));
	private static final long BIT_MASK = (long) Math.pow(2, BITS) - 1;
	
	private final IBoard board;
	private final MSNode root;
	
	public class MSNode implements Node {
		private final Color playerColor;
		private final long[] boardState; // représentation compact du plateau
		private double evaluation;
		private List<Node> children; // null => enfants non générés
		
		// CONSTRUCTEURS
		public MSNode(MSNode parent, Coord move, double ev) {
			if (parent == null || move == null) {
				throw new AssertionError();
			}
			playerColor = Color.values()[(parent.playerColor.ordinal() + 1) % Color.values().length];
			children = null;
			evaluation = ev;
			boardState = parent.boardState.clone();
			//System.out.println(playerColor + ":cons " + move.row() + " " + move.col());
        	//System.out.println(playerColor + ":state cons\t" + Long.toBinaryString(boardState[0]) + " " + Long.toBinaryString(boardState[1]));
			setDisk(move, parent.playerColor);
		}
		
		public MSNode(IBoard b, Color c) {
			if (b == null) {
				throw new AssertionError();
			}
			playerColor = c;
			children = null;
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
			//System.out.println("a=" + a);
        	//System.out.println("getdisk " + move.row() + " " + move.col());
        	//System.out.println(playerColor + ":state getdisk\t" + Long.toBinaryString(boardState[0]) + " " + Long.toBinaryString(boardState[1]));
			return a == 0 ? null :
				Color.values()[(int) a - 1];
		}
		
		public String toString() {
			String s = "eval=" + evaluation + ":" + System.lineSeparator();
			for (int row = 0; row < 8; row++) {
				for (int col = 0; col < 8; col++) {
					s += getDisk(new Coord(row, col)) + "\t";
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
			updateCell(move, c);
		    for (Coord card : Coord.CARDINALS) {
            	//System.out.println("setDisk " + move.plus(card).row() + " " + move.plus(card).col());
		        for (Coord x = move.plus(card); getDisk(x) != c && getDisk(x) != null; x = x.plus(card)) {
	            	//System.out.println("setDisk2 " + move.plus(card).row() + " " + move.plus(card).col());
		            updateCell(x, c);
		        }
		    }
		}
		
		public void generateChildren() {
			children = new ArrayList<Node>();

			for (Coord d : getDisksOfPlayer()) {
		        for (Coord card : Coord.CARDINALS) {
		            Coord x = d.plus(card);
		            if (board.isValid(x) && getDisk(x) != playerColor && getDisk(x) != null) {
		                x = x.plus(card);
		                while (board.isValid(x) && getDisk(x) != playerColor && getDisk(x) != null) {
		                    x = x.plus(card);
		                }
			        	//System.out.println(playerColor + ":state gen\t\t" + Long.toBinaryString(boardState[0]) + " " + Long.toBinaryString(boardState[1]));
		                if (board.isValid(x) && getDisk(x) == null) {
		                	//System.out.println(playerColor + ":generate " + x.row() + " " + x.col());
		                    children.add(new MSNode(this, x, evaluation));
		                }
		            }
		        }
		    }
		}
		
		// OUTILS
		private void updateCell(Coord move, Color c) {
			assert(move != null && c != null);
			assert(move.isInRect(new Coord(board.getSize(), board.getSize())));

			//System.out.println(playerColor + ":update " + move.row() + " " + move.col() + " " + Long.toBinaryString((c.ordinal() + 1) << (shift(move)%Long.SIZE)));
			long a = ((long) (c.ordinal() + 1)) << (shift(move) % Long.SIZE);
			long b = ((long) BIT_MASK) << (shift(move) % Long.SIZE);
			boardState[shift(move) / Long.SIZE] &= ~b;
			boardState[shift(move) / Long.SIZE] |= a;
        	//System.out.println(playerColor + ":state update\t" + Long.toBinaryString(boardState[0]) + " " + Long.toBinaryString(boardState[1]));
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
		
		private Set<Coord> getDisksOfPlayer() {
			int size = board.getSize();
			Set<Coord> res = new HashSet<Coord>();
			
			for (int row = 0; row < size; row++) {
				for (int col = 0; col < size; col++) {
					Coord coord = new Coord(row, col);
					if (getDisk(coord) == playerColor) {
						res.add(coord);
					}
				}
			}
			System.out.println(res.size());
			return res;
		}
		
	}
	
	// CONSTRUCTEURS
	public MinSpaceStrategyTree(IBoard board) {
		this.board = board;
		this.root = new MSNode(board, Color.BLACK);
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
		b.putDisk(new Coord(3,3), Color.BLACK);
		b.putDisk(new Coord(4,4), Color.BLACK);
		b.putDisk(new Coord(3,4), Color.WHITE);
		b.putDisk(new Coord(4,3), Color.WHITE);
		
		MinSpaceStrategyTree t = new MinSpaceStrategyTree(b);
		Node m = t.getRoot();
		m.generateChildren();
		
		System.out.println(m);
		for (Node n : m) {
			System.out.println(m.children().size());
			System.out.println(n);
		}
	}
	
}
