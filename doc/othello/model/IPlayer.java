package othello.model;

import othello.util.Color;
import othello.util.Coord;

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
	 * Retourne si le joueur joue
	 */
	public boolean isPlaying();
	
	/**
	 * Le joueur commence son tour
	 */
	public void startTurn();

	/**
	 * Le joueur a fini son tour
	 */
	public void finishTurn();
	
	/**
	 * Le joueur joue son tour, la coordonnée xy est 
	 */
	public void play(Coord xy);
	
	/**
	 * Le joueur choisit de poser le pion en xy
	 */
	public void choose(Coord xy);
}
