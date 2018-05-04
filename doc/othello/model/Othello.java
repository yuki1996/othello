package othello.model;

import java.util.HashSet;
import java.util.Set;

import othello.util.Color;
import othello.util.Coord;

public class Othello implements IOthello {
	
	private IPlayer playerBlackpass, playerWhitepass;
	private IBoard myBoard;
	private IPlayer currentPlayer;
	
	//Jeu avec 2 humains
	public Othello() {
		myBoard = new Board(8);
		playerBlackpass = SpawnPlayer(Color.BLACK);
		playerWhitepass = SpawnPlayer(Color.WHITE);
		currentPlayer = playerBlackpass;
	}
	
	//jeu avec 1 humain et 1 IA, l'humain joue d'abord
	public Othello(String strategie, int niveau) {
		myBoard = new Board(8);
		playerBlackpass = SpawnPlayer(Color.BLACK);
		playerWhitepass = SpawnAI(Color.WHITE, strategie, niveau);
		currentPlayer = playerBlackpass;
	}
	
	//Jeu avec 2 IA
	public Othello(String strategieP1, int niveauP1, String strategieP2, int niveauP2) {
		myBoard = new Board(8);
		playerBlackpass = SpawnAI(Color.BLACK, strategieP1, niveauP1);
		playerWhitepass = SpawnAI(Color.WHITE, strategieP2, niveauP2);
		currentPlayer = playerBlackpass;
	}
	
	//REQUETES
	public boolean isGameOver() {
		if (!foePlayed() && !canPlay(currentPlayer)) {
			return true;
		} 
		return false;
	}

	public IPlayer getCurrentPlayer() {
		return currentPlayer;
	}
	
	public boolean foePlayed() {
		return true;
	}

	public Set<Coord> getValidMoves(Color player_color){
		return new HashSet<Coord>();
	}
	
	public IBoard getBoard() {
		return myBoard;
	}
	
	public boolean canPlay(IPlayer p) {
		if (currentPlayer == p && !getValidMoves(p.getColor()).isEmpty()) {
			return true;
		}
		return false;
	}
	
	//METHODES
	public void restart() {
		myBoard = new Board(8);
	}
	
	public void turn() {
		if (!isGameOver()) {
			throw new IllegalArgumentException("fin du jeu");
		} else if (canPlay(currentPlayer)) {
			currentPlayer.play();
		}
		currentPlayer = (currentPlayer == playerBlackpass ? playerWhitepass : playerBlackpass);
	}
	
	//OUTILS
	/**
	 * Créer un joueur humain
	 */
	private IPlayer SpawnPlayer(Color player_color) {
		return new Human(player_color, getBoard());
	}
	
	/**
	 * Créer une IA
	 */
	private IPlayer SpawnAI(Color player_color, String strategie, int niveau) {
		return new AI(player_color, getBoard());
	}
}