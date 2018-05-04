package othello.model;

import othello.util.Color;
import othello.util.Coord;

public interface IBoard {
	
	/**
	 * Retourne la longueur / largeur du plateau de jeu 
	 */
	int getSize();
	
	/**
	 * Retourne si le mouvement du joueur est valide. 
	 * La coordonnée doit être valide, la case est soit vide ou soit la color de l'adversaire
	 * et le mouvement doit rapporter des pions de l'adversaire.
	 */
	boolean isValidMove(Coord xy, Color color);
	
	/**
	 * Retourne la couleur de la case du plateau
	 */
	Color getColor(Coord c);
	
	/**
	 * Retourne si la coordonnée est valide dans le plateau
	 */
	boolean isValid(Coord xy);
	
	/**
	 * Pose le pion
	 * @pre : isValidMove(xy, color)
	 */
	void putDisk(Coord xy, Color color);
	
}