package othello.model;

import othello.util.Color;
import othello.util.Coord;

public class Human extends abstract_Player{
		
	Human(Color myColor, IBoard board){
		super(myColor, board);
	}

	@Override
	public void play(Coord xy) {
		choose(xy);
	}
}