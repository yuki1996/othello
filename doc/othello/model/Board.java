package othello.model;

import othello.util.Color;
import othello.util.Coord;

public class Board implements itf_Board{
	
	private int size;

	private Color coord_color[][]; //= new Color[getSize()][getSize()];
	
	public boolean isValidMove() {
		return true;
	}
	
	public Color getColor(Coord c) {
		return coord_color[c.row()][c.col()];
	}
	
	public boolean isValid(Coord xy) {
		return true;
	}
	
	public void putDisk(Coord xy, Color color) {
		
	}
	
	public int getSize() {
		return this.size;
	}
	
	Board(int size){
		this.size = size;
		final Color coord_color[][] = new Color [this.size][this.size];
	}
}