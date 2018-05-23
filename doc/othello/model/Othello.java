package othello.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import othello.util.Color;
import othello.util.Coord;

public class Othello implements IOthello {
	
	private IPlayer playerBlack, playerWhite;
	private IBoard myBoard;
	private IPlayer currentPlayer;
	private PropertyChangeSupport propertySupport;
	
	//Jeu avec 2 humains
	public Othello() {
		myBoard = new Board(8);
		playerBlack = spawnPlayer(Color.BLACK);
		playerWhite = spawnPlayer(Color.WHITE);
		propertySupport = new PropertyChangeSupport(this);
		initialisationBoard();
		currentPlayer = playerBlack;
	}
	
	//jeu avec 1 humain et 1 IA, l'humain joue d'abord
	public Othello(Color colorHumain, String strategie, int niveau) {
		if (colorHumain == null) {
			throw new IllegalArgumentException("le joueur humain doit avoir une couleur");
		}
		myBoard = new Board(8);
		playerBlack = spawnPlayer(colorHumain);
		playerWhite = spawnAI(colorHumain == Color.BLACK ? Color.WHITE : Color.BLACK, strategie, niveau);
		propertySupport = new PropertyChangeSupport(this);
		initialisationBoard();
		currentPlayer = playerBlack;
	}
	
	//Jeu avec 2 IA
	public Othello(String strategieP1, int niveauP1, String strategieP2, int niveauP2) {
		propertySupport = new PropertyChangeSupport(this);
		myBoard = new Board(8);
		playerBlack = spawnAI(Color.BLACK, strategieP1, niveauP1);
		playerWhite = spawnAI(Color.WHITE, strategieP2, niveauP2);
		propertySupport = new PropertyChangeSupport(this);
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
		initialisationBoard();
		currentPlayer = playerBlack;
	}
	
	public void turn(Coord xy) {
		if (isGameOver()) {
			throw new IllegalArgumentException("fin du jeu");
		} else if (canPlay(currentPlayer)) {
			IBoard oldBoard = getBoard();
			currentPlayer.play(xy);
			propertySupport.firePropertyChange(BOARD, oldBoard, getBoard());
		}
		IPlayer oldCurrentPlayer = currentPlayer;
		currentPlayer = (oldCurrentPlayer == playerBlack ? playerWhite : playerBlack);
		propertySupport.firePropertyChange(PLAYER, oldCurrentPlayer, currentPlayer);
	}
	
	public void addPropertyChangeListener(String property, PropertyChangeListener l) {
		if (l == null) {
			throw new IllegalArgumentException("l'écouteur est null");
		}
		propertySupport.addPropertyChangeListener(property, l);
	}
	
	public void removePropertyChangeListener(String property, PropertyChangeListener l) {
		if (l == null) {
			throw new IllegalArgumentException("l'écouteur est null");
		}
		propertySupport.removePropertyChangeListener(property, l);
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
		IBoard oldBoard = getBoard();
		//D4(3,3) pion blanc
		myBoard.putDisk(new Coord(3,3), Color.WHITE);
		//E4(3,4) pion noir
		myBoard.putDisk(new Coord(3,4), Color.BLACK);
		//D5(4,3) pion noir
		myBoard.putDisk(new Coord(4,3), Color.BLACK);
		//E5(4,4) pion blanc
		myBoard.putDisk(new Coord(4,4), Color.WHITE);
		propertySupport.firePropertyChange(BOARD, oldBoard, getBoard());
	}
}
