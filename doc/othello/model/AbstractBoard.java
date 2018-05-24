package othello.model;

import java.util.HashSet;
import java.util.Set;

import othello.util.Color;
import othello.util.Coord;

public abstract class AbstractBoard implements IBoard {

	protected final int size;
	private Coord lastShot;
	
	protected AbstractBoard(int size) {
		this.size = size;
		this.lastShot = null;
	}
	
	public int getSize() {
		return this.size;
	}

	public boolean isValid(Coord xy) {
		return xy.isInRect(new Coord(size, size));
	}

	public boolean isFull() {
		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++) {
				if (getColor(new Coord(row, col)) == null) {
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
	
	public Coord getLastShot() {
		return lastShot;
	}
	
	public void playAShot(Coord xy, Color color) {
		if (!isValidMove(xy, color)) {
			throw new IllegalArgumentException("Pose invalide");
		}

		//couleur adversaire;
		Color foeColor = color == Color.WHITE ? Color.BLACK : Color.WHITE;
		putDisk(xy, color);
		lastShot = xy;
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
	
	public String toString() {
		String s = "";
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				s += getColor(new Coord(row, col)) + "\t";
			}
			s += System.lineSeparator();
		}
		return s;
	}
	
	//OUTILS
	/**
	 * Retourne un tableau contenant les coordonnées jouée par le joueur
	 */
	protected Set<Coord> getDisksOfPlayer(Color colorPlayer) {
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