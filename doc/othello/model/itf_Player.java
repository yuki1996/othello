package othello.model;

import othello.util.Color;

public interface itf_Player{
//	final Color myColor = p;
	Color getColor();
	void Play();
	Board getBoard();
}