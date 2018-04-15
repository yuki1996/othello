package othello.model;

import othello.util.Color;

public interface Player{
//	final Color myColor = p;
	Color getColor();
	void abstractPlayer();
	Board getBoard();

}