package othello.model;

import java.util.HashSet;
import java.util.Set;

import othello.util.Color;
import othello.util.Coord;

public class Othello implements itf_Othello{
	boolean playerBlackpass,playerWhitepass;
	
	public boolean isGameOver(itf_Player moi) {
		if(!foePlayed() && !canPlay(moi)) {
			return true;
		}else {
			return false;
		}
		
	}

	public boolean foePlayed() {
		return true;
	}
	//restart();
	public Set<Coord> getValidMoves(Color player_color){
		return new HashSet<Coord>();
	}
	public Board getBoard() {
		return new Board(8);
	}
	
	public itf_Player SpawnPlayer(Color player_color) {
		return new human(Color.BLACK);
	}
	public itf_Player SpawnAI(String strategie, int niveau) {
		return new AI(Color.BLACK);
	}
	
	public boolean canPlay(itf_Player p) {
		return true;
	}
}