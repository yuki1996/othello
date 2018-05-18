package othello.model;

import java.util.Iterator;
import java.util.Set;

import othello.util.Color;
import othello.util.Coord;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Othello game = new Othello();
		System.out.println(" 01234567");
		for (int i = 0; i < 8; i++) {
			System.out.print(i);
			for (int j = 0; j < 8; j++) {
				Color c =game.getBoard().getColor(new Coord(i,j));
				if (c == null) {
					System.out.print("-");
				} else if (c == Color.BLACK) {
					System.out.print("B");
				} else {
					System.out.print("W");
				}
				
			}
			System.out.println();
		}
		System.out.println();System.out.println();System.out.println();
		game.turn(new Coord(2,3));
		System.out.println(" 01234567");
		for (int i = 0; i < 8; i++) {
			System.out.print(i);
			for (int j = 0; j < 8; j++) {
				Color c =game.getBoard().getColor(new Coord(i,j));
				if (c == null) {
					System.out.print("-");
				} else if (c == Color.BLACK) {
					System.out.print("B");
				} else {
					System.out.print("W");
				}
				
			}
			System.out.println();
		}
		Set<Coord> set = game.getBoard().getValidMoves(Color.WHITE);
		Iterator<Coord> it = set.iterator();
		while (it.hasNext()) {
			Coord coord = (Coord) it.next();
			System.out.println(coord.row()+ ";" + coord.col());
		}
		
	}

}
