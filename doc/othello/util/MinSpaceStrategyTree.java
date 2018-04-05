package othello.util;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class MinSpaceStrategyTree implements StrategyTree {
	private final Board board;
	private final MSNode root;
	
	public class MSNode implements Node {
		private final List<StrategyTree> children;
		private final byte[] boardState;
		private int evaluation;

		public MSNode(int ev, byte[] parentState) {
			children = new LinkedList<StrategyTree>();
			evaluation = ev;
			boardState = f(parentState);// TODO
		}
		
		public Iterator<StrategyTree> iterator() {
			return children.iterator();
		}
		
		public List<StrategyTree> children() {
			return children;
		}
		
		public int getEval() {
			return evaluation;
		}

		public void setEval(int e) {
			if (e < 0) {
				throw new AssertionError();
			}
			evaluation = e;
		}
		
	}
	
	public MinSpaceStrategyTree(Board board) {
		this.board = board;
		this.root = new MSNode();
	}
	
	public Board getBoard() {
		return Board;
	}
	
}
