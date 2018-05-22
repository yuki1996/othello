package othello.model;

import othello.util.Color;
import othello.util.Coord;

abstract class AbstractPlayer implements IPlayer{
	
	protected final Color myColor;
	protected final IBoard board;
	protected boolean isPlaying;
	
	AbstractPlayer(Color myColor, IBoard board){
		this.myColor = myColor;
		this.board = board;
	}
	
	public Color getColor() {
		return this.myColor;
	}
	
	public IBoard getBoard() {
		return this.board;
	}
	
	public boolean isPlaying() {
		return isPlaying;
	}
	
	public void choose(Coord xy) {
		if (!board.isValidMove(xy, myColor)) {
			throw new IllegalArgumentException("impossible de poser le pion!");
		}
		board.playAShot(xy, myColor);
	}
	
	public void startTurn() {
		isPlaying = true;
	}

	public void finishTurn() {
		isPlaying = false;
	}
	
	public abstract void play(Coord xy);
	
}