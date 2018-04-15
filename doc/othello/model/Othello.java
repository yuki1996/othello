package othello.model;

import java.util.Set;

import othello.util.Color;
import othello.util.Coord;

public interface Othello {
	boolean isGameOver();
	boolean foePlayed();
	//restart();
	Set<Coord> getValidMoves(Color player_color);
	Board getBoard();
	Player SpawnPlayer();
	Player SpawnAI(String strategie, int niveau);
}