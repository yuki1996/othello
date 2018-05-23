package othello.view;

import java.util.Iterator;
import java.util.Set;

import othello.model.Othello;
import othello.util.Color;
import othello.util.Coord;

public class Play {

	public static void main(String[] args) {
		Menu menu = new Menu();
		/*Othello o = new Othello();
		
		for (int i = 0; i < 8; i++) {
			System.out.print(i);
			for (int j = 0; j < 8; j++) {
				Color c =o.getBoard().getColor(new Coord(i,j));
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
		
		//Affichage du jeu : joue 60 tours (fin partie) ou jusqu'un des 2 joueurs peut pas jouer son tour
		Set<Coord> set = o.getBoard().getValidMoves(Color.BLACK);
		int k = 0;
		while (!set.isEmpty() && k < 60) {
			Iterator<Coord> it = set.iterator();
			System.out.println();

			Coord coord = (Coord) it.next();
			System.out.println("Tour "+ k+ " "+(k % 2 != 0 ? Color.WHITE : Color.BLACK) + " joue " +coord.row()+ ";" + coord.col());
			o.turn(coord);
			System.out.println(" 01234567");
			for (int i = 0; i < 8; i++) {
				System.out.print(i);
				for (int j = 0; j < 8; j++) {
					Color c =o.getBoard().getColor(new Coord(i,j));
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
			++k;
			set = o.getBoard().getValidMoves( k % 2 != 0 ? Color.WHITE : Color.BLACK);
		}

		PopUpResult popup = new PopUpResult(o);*/
	}

}
