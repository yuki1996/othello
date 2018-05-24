package othello.util;

import java.util.List;

import othello.model.IBoard;

/**
 * 
 * @author Loïc Frétard
 * 
 * Classe représentant les arbres utilisés pour les stratégies.
 * 
 * @inv
 * 	getBoard() != null
 * 	getRoot() != null
 */
public interface StrategyTree {
	/**
	 * Classe représentant un noeud/une feuille de l'arbre.
	 * On peut parcourir les enfants d'un Node en le mettant dans un for-each.
	 * 
	 * @inv
	 * 	children() != null
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
		 * Renvoie la couleur de la case de coordonnée move dans l'état
		 * contenu dans le noeud.
		 */
		Color getDisk(Coord move);
		
		/**
		 * Pose un jeton de couleur c dans la case move du plateau du noeud
		 * avec retournement des jetons adverses.
		 */
		void setDisk(Coord move, Color c);
		
		/**
		 * Génère tous les noeuds enfants correspondant à un coup possible.
		 */
		void generateChildren();
	}
	
	/**
	 * Renvoie le Board associé à cet arbre.
	 */
	IBoard getBoard();
	
	/**
	 * Renvoie la racine de l'arbre.
	 */
	Node getRoot();
}
