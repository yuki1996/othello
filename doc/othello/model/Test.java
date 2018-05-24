package othello.model;

import java.util.Iterator;
import java.util.Set;

import othello.util.Color;
import othello.util.Coord;
import othello.view.PopUpResult;

public class Test {

	public static void main(String[] args) {
		Othello game = new Othello();
		
		//affichage de partie
		System.out.println("Partie de départ \n");
		printBoard(game);
		
		//Affichage du jeu : joue 60 tours (fin partie) ou jusqu'un des 2 joueurs peut pas jouer son tour
		Set<Coord> set = game.getBoard().getValidMoves(Color.BLACK);
		int k = 0;
		while (!set.isEmpty() && k < 60) {
			Iterator<Coord> it = set.iterator();
			System.out.println();

			Coord coord = (Coord) it.next();
			System.out.println("Tour "+ k+ " "+(k % 2 != 0 ? Color.WHITE : Color.BLACK) + " joue " +coord.row()+ ";" + coord.col());
			game.turn(coord);
			printBoard(game);
			++k;
			set = game.getBoard().getValidMoves( k % 2 != 0 ? Color.WHITE : Color.BLACK);
		}
		
		PopUpResult pop = new PopUpResult(game);
		
		
		game.restart();
		System.out.println("\n\nRecommence la partie");
		printBoard(game);
		
		//Affichage du jeu : joue 60 tours (fin partie) ou jusqu'un des 2 joueurs peut pas jouer son tour
		set = game.getBoard().getValidMoves(Color.BLACK);
		k = 0;
		while (!set.isEmpty() && k < 2) {
			Iterator<Coord> it = set.iterator();
			System.out.println();

			Coord coord = (Coord) it.next();
			System.out.println("Tour "+ k+ " "+(k % 2 != 0 ? Color.WHITE : Color.BLACK) + " joue " +coord.row()+ ";" + coord.col());
			game.turn(coord);
			printBoard(game);
			++k;
			set = game.getBoard().getValidMoves( k % 2 != 0 ? Color.WHITE : Color.BLACK);
		}
		
	}
	
	//outils
	/**
	 * Affichage la grille
	 */
	private static void printBoard(Othello game) {
		System.out.println(" 01234567");
		for (int i = 0; i < 8; i++) {
			System.out.print(i);
			for (int j = 0; j < 8; j++) {
				Color c = game.getBoard().getColor(new Coord(i,j));
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
		
	}

}
