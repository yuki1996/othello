package othello.model;

import othello.util.Color;
import othello.util.Coord;

public class Board implements IBoard{
	
	private int size;

	private Color coord_color[][]; //= new Color[getSize()][getSize()];
	
	Board(int size){
		this.size = size;
		coord_color = new Color [this.size][this.size];
	}

	//REQUÊTES
	public int getSize() {
		return this.size;
	}
	
	public Color getColor(Coord c) {
		return coord_color[c.row()][c.col()];
	}
	
	public boolean isValidMove(Coord xy, Color color) {
		if (isValid(xy)) {
			return false;
		}
		if (getColor(xy) == color) {
			return false;
		}
		return true;
	}
	
	public boolean isValid(Coord xy) {
		if (0 <= xy.row() && xy.row() < getSize() 
			&& 0 <= xy.col() && xy.col() < getSize()) {
			return true;
		}
		return false;
	}
	
	//METHODES
	public void putDisk(Coord xy, Color color) {
		if (!isValidMove(xy, color)) {
			throw new IllegalArgumentException("mouvement impossible");
		}
		coord_color[xy.row()][xy.col()] = color;
	}
	
}