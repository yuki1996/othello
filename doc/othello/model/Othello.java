package othello.model;

import java.util.HashSet;
import java.util.Set;

import othello.util.Color;
import othello.util.Coord;

public class Othello implements IOthello {
	
	private IPlayer playerBlack, playerWhite;
	private IBoard myBoard;
	private IPlayer currentPlayer;
	
	//Jeu avec 2 humains
	public Othello() {
		myBoard = new Board(8);
		playerBlack = spawnPlayer(Color.BLACK);
		playerWhite = spawnPlayer(Color.WHITE);
		initialisationBoard();
		currentPlayer = playerBlack;
	}
	
	//jeu avec 1 humain et 1 IA, l'humain joue d'abord
	public Othello(String strategie, int niveau) {
		myBoard = new Board(8);
		playerBlack = spawnPlayer(Color.BLACK);
		playerWhite = spawnAI(Color.WHITE, strategie, niveau);
		initialisationBoard();
		currentPlayer = playerBlack;
	}
	
	//Jeu avec 2 IA
	public Othello(String strategieP1, int niveauP1, String strategieP2, int niveauP2) {
		myBoard = new Board(8);
		playerBlack = spawnAI(Color.BLACK, strategieP1, niveauP1);
		playerWhite = spawnAI(Color.WHITE, strategieP2, niveauP2);
		initialisationBoard();
		currentPlayer = playerBlack;
	}
	
	//REQUETES
	public boolean isGameOver() {
		if (foePlayed() && !canPlay(currentPlayer)) {
			return true;
		} 
		return false;
	}

	public IPlayer getCurrentPlayer() {
		return currentPlayer;
	}
	
	public boolean foePlayed() {
		IPlayer foe = (currentPlayer == playerBlack ? playerWhite : playerBlack);
		return !foe.isPlaying();
	}
	
	public IBoard getBoard() {
		return myBoard;
	}
	
	public boolean canPlay(IPlayer p) {
		if (currentPlayer == p && !myBoard.getValidMoves(p.getColor()).isEmpty()) {
			return true;
		}
		return false;
	}
	
	public Color isWinner() {
		if (!isGameOver()) {
			throw new IllegalArgumentException("partie pas fini");
		} 
		int nbPointWhite = myBoard.getPointsPlayer(Color.WHITE);
		int nbPointBlack = myBoard.getPointsPlayer(Color.BLACK);
		System.out.println("white :" + nbPointWhite + "/ black :"+nbPointBlack);
		if (nbPointWhite == nbPointBlack) {
			return null;
		} else if (nbPointWhite > nbPointBlack) {
			return Color.WHITE;
		} 
		return Color.BLACK;
				
		
	}
	
	//METHODES
	public void restart() {
		myBoard = new Board(8);
	}
	
	public void turn(Coord xy) {
		if (isGameOver()) {
			throw new IllegalArgumentException("fin du jeu");
		} else if (canPlay(currentPlayer)) {
			currentPlayer.play(xy);
		}
		currentPlayer = (currentPlayer == playerBlack ? playerWhite : playerBlack);
	}
	
	//OUTILS
	/**
	 * Créer un joueur humain
	 */
	private IPlayer spawnPlayer(Color player_color) {
		return new Human(player_color, getBoard());
	}
	
	/**
	 * Créer une IA
	 */
	private IPlayer spawnAI(Color player_color, String strategie, int niveau) {
		return new AI(player_color, getBoard());
	}
	
	/**
	 * Initialise le plateau avec les 4 pièces au départ.
	 */
	private void initialisationBoard() {
		//D4(3,3) pion blanc
		myBoard.putDisk(new Coord(3,3), Color.WHITE);
		//E4(3,4) pion noir
		myBoard.putDisk(new Coord(3,4), Color.BLACK);
		//D5(4,3) pion noir
		myBoard.putDisk(new Coord(4,3), Color.BLACK);
		//E5(4,4) pion blanc
		myBoard.putDisk(new Coord(4,4), Color.WHITE);
	}
}