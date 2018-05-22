package othello.model;

import othello.util.Color;
import othello.util.Coord;

public class Human extends AbstractPlayer{
		
	Human(Color myColor, IBoard board){
		super(myColor, board);
	}

	@Override
	public void play(Coord xy) {
		startTurn();
		choose(xy);
		finishTurn();
	}
}
