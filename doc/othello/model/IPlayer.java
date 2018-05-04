package othello.model;

import othello.util.Color;

public interface IPlayer {
	
	/**
	 * Retourne la couleur du joueur
	 */
	public Color getColor();
	
	/**
	 * Retourne le plateau de jeu
	 */
	public IBoard getBoard();
	
	/**
	 * Le joueur joue son tour
	 */
	public void play();
}