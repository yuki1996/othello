package othello.model;

import othello.util.Color;
import othello.util.Coord;

abstract class abstract_Player implements IPlayer{
	
	protected final Color myColor;
	protected final IBoard board;
	
	abstract_Player(Color myColor, IBoard board){
		this.myColor = myColor;
		this.board = board;
	}
	
	public Color getColor() {
		return this.myColor;
	}
	
	public IBoard getBoard() {
		return this.board;
	}
	
	public abstract void play(Coord xy);
	
	public void choose(Coord xy) {
		if (!board.isValidMove(xy, myColor)) {
			throw new IllegalArgumentException("impossible de poser le pion!");
		}
		board.putDisk(xy, myColor);
	}
}