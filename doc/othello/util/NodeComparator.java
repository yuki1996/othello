package othello.util;

import java.util.Comparator;

class NodeComparator implements Comparator<StrategyTree.Node> {

	@Override
	public int compare(StrategyTree.Node n1, StrategyTree.Node n2) {
		if (n1.getPlayerColor() != n2.getPlayerColor()) {
			throw new AssertionError("Comparaison entre deux neuds de couleurs différentes");
		}
		
		if (n1.getPlayerColor() == Color.BLACK) {
			if (n1.getEval() - n2.getEval() < 0) {
				return -1;
			} else if (n1.getEval() - n2.getEval() > 0) {
				return 1;
			} else {
				return 0;
			}
		} else {
			if (n2.getEval() - n1.getEval() < 0) {
				return -1;
			} else if (n1.getEval() - n2.getEval() > 0) {
				return 1;
			} else {
				return 0;
			}
		}
	}
	
}