package othello.model;

import othello.util.Color;

abstract class abstract_Player implements Player{
	
	final Color myColor;
	
	abstract_Player(Color myColor){
		this.myColor = myColor;
	}
}