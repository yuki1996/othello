package othello.model;

import othello.util.Color;

abstract class abstract_Player implements itf_Player{
	
	protected final Color myColor;
	
	abstract_Player(Color myColor){
		this.myColor = myColor;
	}
	
	public Color getColor() {
		return this.myColor;
	}
	
	public Board getBoard() {
		return new Board(8);
	}
	
	public void Play() {
		
	}
}