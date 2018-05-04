package othello.model;

import java.util.Set;

import othello.util.Color;
import othello.util.Coord;

public interface IOthello {
	
	/**
	 * Retourne si le jeu est fini
	 */
	public boolean isGameOver();
	
	/**
	 * Retourne si le joueur adversaire a joué
	 */
	public boolean foePlayed();
	
	/**
	 * Retourne les mouvements valides du joueur de la couleur player_color
	 */
	public Set<Coord> getValidMoves(Color player_color);
	
	/**
	 * Retourne le plateau de jeu
	 */
	public IBoard getBoard();
	
	/**
	 * Retourne le joueur courant
	 */
	public IPlayer getCurrentPlayer();
	
	/**
	 * Retourne si le joueur p peut joueur
	 */
	public boolean canPlay(IPlayer p);
	
	/**
	 * Recommence la partie
	 */
	public void restart();
	
	/**
	 * Le joueur courant joue un tour. S'il peut pas jouer mais son adversaire si alors
	 * le joueur passe juste son tour.
	 * @pre : !isGameOver()
	 */
	public void turn();
}