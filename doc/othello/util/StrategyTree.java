package othello.util;

import java.util.List;

import othello.model.Board;

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
		
		/**
		 * Renvoie la couleur de la case de coordonnée c dans l'état
		 * contenu dans le noeud.
		 */
		Color getDisk(Coord move);
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
