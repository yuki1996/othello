package othello.util;

/**
 * 
 * @author Loïc Frétard
 *
 * Classe représentant une coordonée sur le plateau de jeu.
 * classe non-mutable
 *
 * @inv
 * 	x >= 0 && y >= 0
 */
public class Coord {
	private int x;
	private int y;
	
	public Coord(int x, int y) {
		if (x < 0 || y < 0) {
			throw new AssertionError();
		}
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Addition de deux coordonnées.
	 * @post
	 * 	return == Coord(c1.x + c2.x, c1.y + c2.y)
	 */
	public Coord add(Coord c1, Coord c2) {
		return new Coord(c1.x + c2.x, c1.y + c2.y);
	}
	
	public boolean equals(Object obj) {
		if (obj != null && this.getClass() == obj.getClass()) {
			Coord c = (Coord) obj;
			return x == c.x && y == c.y;
		}
		return false;
	}
	
	public int hashCode() {
		return 7 * x + y;
	}
}
