package othello.model;

import othello.util.Color;
import othello.util.Coord;

public class Board extends AbstractBoard {
	
	private final Color coord_color[][]; //= new Color[getSize()][getSize()];
	
	public Board(int size){
		super(size);
		coord_color = new Color [this.size][this.size];
	}

	//REQUÊTES
	
	public Color getColor(Coord c) {
		return coord_color[c.row()][c.col()];
	}
	
	//METHODES
	
	public void putDisk(Coord xy, Color color) {
		if (!isValid(xy)) {
			throw new IllegalArgumentException("mouvement impossible");
		}
		coord_color[xy.row()][xy.col()] = color;
	}
	
	
}
