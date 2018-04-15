package othello.model;

import java.util.Set;

import othello.util.Color;
import othello.util.Coord;

public interface itf_Othello {
	boolean isGameOver(itf_Player moi);
	boolean foePlayed();
//	restart();
	Set<Coord> getValidMoves(Color player_color);
	Board getBoard();
	itf_Player SpawnPlayer(Color player_color);
	itf_Player SpawnAI(String strategie, int niveau);
	boolean canPlay(itf_Player p);
}