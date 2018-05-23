package othello.model;

import java.util.HashSet;
import java.util.Set;

import othello.util.Color;
import othello.util.Coord;

public class Board implements IBoard {
	
	private final int size;

	private final Color coord_color[][]; //= new Color[getSize()][getSize()];
	
	public Board(int size){
		this.size = size;
		coord_color = new Color [this.size][this.size];
	}

	//REQUÊTES
	public int getSize() {
		return this.size;
	}
	
	public Color getColor(Coord c) {
		return coord_color[c.row()][c.col()];
	}
	
	public boolean isFull() {
		for (Color[] t : coord_color) {
			for (Color c : t) {
				if (c == null) {
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean isValidMove(Coord xy, Color color) {
		if (!isValid(xy)) {
			return false;
		}
		if (getColor(xy) != null) {
			return false;
		}
		return getValidMoves(color).contains(xy);
	}
	
	public Set<Coord> getValidMoves(Color player_color){
		Set<Coord> ret = new HashSet<Coord>();
		//couleur adversaire;
		Color foeColor = player_color == Color.WHITE ? Color.BLACK : Color.WHITE;
		
		//parcourt pour chaque pion du joueur s'il peut jouer dans le pronlongement dans une certaine direction
	    for (Coord d : getDisksOfPlayer(player_color)) {
	        for (Coord card : Coord.CARDINALS) {
	            Coord x = d.plus(card);
	            //piece, à coté en fct de la cardinalité, est de la couleur de l'adservaire
	            if (isValid(x) && getColor(x) == foeColor) {
	                x = x.plus(card);
	                //on continue à avancer tant que la couleur est celle de l'adversaire
	                while (isValid(x) && getColor(x) == foeColor) {
	                    x = x.plus(card);
	                }
	                //on peut sur la coordonéee que si elle est libre
	                if (isValid(x) && getColor(x) == null) {
	                    ret.add(x);
	                }
	            }
	        }
	    }
	    return ret;
	}
	
	public boolean isValid(Coord xy) {
		if (0 <= xy.row() && xy.row() < getSize() 
			&& 0 <= xy.col() && xy.col() < getSize()) {
			return true;
		}
		return false;
	}
	
	public int getPointsPlayer(Color colorPlayer) {
		int result = 0 ;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				Coord xy = new Coord(i,j);
				if (getColor(xy) == colorPlayer) {
					++result;
				}
			}
		}
		return result;
	}
	
	//METHODES
	public void playAShot(Coord xy, Color color) {
		if (!isValidMove(xy, color)) {
			throw new IllegalArgumentException("Pose invalide");
		}

		//couleur adversaire;
		Color foeColor = color == Color.WHITE ? Color.BLACK : Color.WHITE;
		coord_color[xy.row()][xy.col()] = color;
		for (Coord card : Coord.CARDINALS) {
			Coord x = xy.plus(card);
			//On verifie qu'il y a un élément à prendre en "sandwich" dans cette direction
			while (isValid(x) && getColor(x) == foeColor) {
	        	x = x.plus(card);
	        }
			//Si le dernier pion est le notre alors on peut retourner les pieces
			if (isValid(x) && getColor(x) == color) {
				x = xy.plus(card);
				while (isValid(x) && getColor(x) == foeColor) {
		        	putDisk(x, color);
		        	x = x.plus(card);
		        }
			}
	    }
	}
	
	public void putDisk(Coord xy, Color color) {
		if (!isValid(xy)) {
			throw new IllegalArgumentException("mouvement impossible");
		}
		coord_color[xy.row()][xy.col()] = color;
	}
	
	//OUTILS
	/**
	 * Retourne un tableau contenant les coordonnées jouée par le joueur
	 */
	private Set<Coord> getDisksOfPlayer(Color colorPlayer) {
		Set<Coord> result = new HashSet<Coord>();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				Coord xy = new Coord(i,j);
				if (getColor(xy) == colorPlayer) {
					result.add(xy);
				}
			}
		}
		return result;
	}
	
}
