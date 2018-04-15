package othello.model;

import othello.util.Color;
import othello.util.Coord;

public interface itf_Board {
	int getSize();
	boolean isValidMove();
	Color getColor(Coord c);
	boolean isValid(Coord xy);
	void putDisk(Coord xy, Color color);
	
}