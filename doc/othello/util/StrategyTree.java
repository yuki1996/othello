package othello.util;

import java.util.Iterator;
import java.util.List;

/**
 * 
 * @author Loïc Frétard
 * 
 * Classe représentant les arbres utilisés pour les stratégies.
 * 
 */
public interface StrategyTree {
	/**
	 * Classe représentant un noeud/une feuille de l'arbre.
	 * On peut parcourir les enfants d'un Node en le mettant dans un for-each.
	 */
	interface Node extends Iterable<Node> {
		/**
		 * renvoie la liste des enfants deu noeud.
		 */
		List<? extends Node> children();
		
		/**
		 * Renvoie l'évaluation du noeud.
		 */
		double getEval();
		
		/**
		 * Modifie l'évaluation du noeud.
		 */
		void setEval(double e);
	}
	
	/**
	 * Renvoie le Board associé à cet arbre.
	 */
	Board getBoard();
	
	/**
	 * Renvoie la racine de l'arbre.
	 */
	Node getRoot();
}
