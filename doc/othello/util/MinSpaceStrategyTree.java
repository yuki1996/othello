package othello.util;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class MinSpaceStrategyTree implements StrategyTree {
	private static final int BITS = 
		(int) Math.ceil(Math.log((Color.values().length + 1)) / Math.log(2.0));
	private final Board board;
	private final MSNode root;
	
	public class MSNode implements Node {
		private final List<Node> children;
		private final long[] boardState;
		private double evaluation;

		public MSNode(MSNode parent, Coord move, Color c, double ev) {
			if (parent == null || move == null || c == null) {
				throw new AssertionError();
			}
			children = new LinkedList<Node>();
			evaluation = ev;
			boardState = parent.boardState.clone();
			setDisk(move, c);
		}
		
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

		public void setEval(double e) {
			evaluation = e;
		}
		
		// OUTILS
		private void setDisk(Coord move, Color c) {
			assert(move != null && c != null);
			assert(move.isInfTo(new Coord(board.getSize(), board.getSize())));
			
			int size = board.getSize();
			//int nbCell = size * size;
			int shift = BITS * move.row() * size + move.col();
			
			long a = (c.ordinal() + 1) << (shift % Long.SIZE);
			boardState[shift / Long.SIZE] |= a;
		}
		
	}
	
	public MinSpaceStrategyTree(Board board) {
		this.board = board;
		this.root = new MSNode();
	}
	
	public Board getBoard() {
		return board;
	}
	
	public Node getRoot() {
		return root;
	}
	
}
