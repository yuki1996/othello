package othello.util;

import java.util.Comparator;

class NodeComparator implements Comparator<StrategyTree.Node> {

	@Override
	public int compare(StrategyTree.Node n1, StrategyTree.Node n2) {
		if (n1.getPlayerColor() != n2.getPlayerColor()) {
			throw new AssertionError("Comparaison entre deux neuds de couleurs différentes");
		}
		
		return n1.getPlayerColor() == Color.BLACK ? n1.getEval() - n2.getEval() : n2.getEval() - n1.getEval();
	}
	
}