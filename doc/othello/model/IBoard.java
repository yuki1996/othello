package othello.model;

import java.beans.PropertyChangeListener;
import java.util.Set;

import othello.util.Color;
import othello.util.Coord;

public interface IBoard {
	
	// PROPRIETES
	String COLOR = "color";
		
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
	 * @pre : isValid(xy)
	 */
	void putDisk(Coord xy, Color color);
	
	/**
	 * Jeu le coup en xy, cad poser le pion, et retourne les pions de l'adversaire correspondant au coup 
	 * @pre : isValidMove(xy, color)
	 */
	void playAShot(Coord xy, Color color);
	
	/**
	 * Retourne les mouvements valides du joueur de la couleur player_color
	 */
	public Set<Coord> getValidMoves(Color player_color);
	
	/**
	 * Retourne le nombre de points de la couleur du joueur
	 */
	public int getPointsPlayer(Color colorPlayer);
	
	/**
	 * Ajoute un écouteur
	 * @pre : l != null
	 */
	void addPropertyChangeListener(String property, PropertyChangeListener l);
	
	
	/**
	 * Retire un écouteur
	 * @pre : l != null
	 */
	void removePropertyChangeListener(String property, PropertyChangeListener l);
}
