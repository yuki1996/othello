package othello.util;

import java.util.Iterator;
import java.util.List;

/**
 * 
 * @author Loïc Frétard
 * 
 * Classe représentant les arbres utilisés pour les stratégies.
 * 
 * 
 */
public interface StrategyTree {
	interface Node extends Iterable<StrategyTree> {
		List<StrategyTree> children();
		int getEval();
		void setEval(int e);
	}
	Board getBoard();
}
