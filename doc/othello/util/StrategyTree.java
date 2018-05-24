package othello.util;

import java.util.List;
import java.util.Queue;
import java.util.Set;

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
	interface Node extends Iterable<Node>, IBoard {
		/**
		 * renvoie la liste des enfants deu noeud.
		 */
		Queue<? extends Node> children();
		
		/**
		 * Renvoie l'évaluation du noeud.
		 */
		double getEval();
		
		/**
		 * Renvoie la couleur du joueur devant jouer à l'état du noeud.
		 */
		Color getPlayerColor();
		
		/**
		 * Renvoie le parent du noeud.
		 */
		Node getParent();
		
		/**
		 * Renvoie la profondeur du noeud dans son arbre.
		 */
		int getDepth();
		
		/**
		 * Renvoie le dernier coup joué.
		 */
		Coord getMove();
		
		/**
		 * Renvoie une liste de tous les jetons.
		 * 
		 * @post
		 * 	return == List( getColor(Coord(0,0)), getColor(Coord(0,1)), ... getColor(Coord(0, size - 1)),
		 * 					getColor(Coord(1, 0)), getColor(Coord(1,1)) ... 
		 * 																	getColor(Coord(size - 1, size - 1)) )
		 */
		List<Color> getAllDisks();
		
		/**
		 * Renvoie l'ensemble des jeton de couleur playerColor.
		 */
		public Set<Coord> getDisksOfPlayer(Color playerColor);
		
		/**
		 * Renvoie le nombre de coups possible depuis cet état
		 */
		
		/**
		 * Modifie l'évaluation du noeud.
		 */
		void setEval(double e);
		
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
	
	/**
	 * Change la racine de l'arbre pour le descendant correspondant au coup c.
	 */
	void move(Coord c);
}
