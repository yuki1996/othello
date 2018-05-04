package othello.model;

import othello.util.Color;

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
	
	public void play() {
		
	}
}