package othello.util;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author Loïc Frétard
 *
 * Classe représentant une coordonée (couple d'entier) sur le plateau de jeu.
 * classe non-mutable
 * 
 * L'origine se trouve en haut à gauche de l'écran
 *   |
 * --+---->
 *   |
 *   |
 *   V
 */
public class Coord {
	public static final Coord ORIGIN = new Coord(0, 0);
	public static final Coord RIGHT = new Coord(0, 1);
	public static final Coord DOWN = new Coord(1, 0);
	public static final Coord LEFT = RIGHT.minus();
	public static final Coord UP = DOWN.minus();
	public static final Coord L_UP = LEFT.add(UP);
	public static final Coord L_DOWN = LEFT.add(DOWN);
	public static final Coord R_UP = RIGHT.add(UP);
	public static final Coord R_DOWN = RIGHT.add(DOWN);
	public static final Set<Coord> CARDINALS = new HashSet<Coord>();
	static {
		CARDINALS.add(LEFT);
		CARDINALS.add(RIGHT);
		CARDINALS.add(UP);
		CARDINALS.add(DOWN);
		CARDINALS.add(L_UP);
		CARDINALS.add(L_DOWN);
		CARDINALS.add(R_UP);
		CARDINALS.add(R_DOWN);
	}
	
	/**
	 * Calcule la distance entre c1 et c2.
	 */
	public static double dist(final Coord c1, final Coord c2) {
		final Coord c = c1.minus(c2);
		return Math.sqrt(c.row * c.row + c.col * c.col);
	}
	
	private final int row;
	private final int col;
	
	// CONSTRUCTEUR
	public Coord(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	// REQUETES
	public int row() { return row; }
	public int col() { return col; }
	
	/**
	 * Renvoie true si this est "plus proche" de l'origine que c.
	 * Ici "plus proche" signifie que this est dans le rectangle
	 * défini par ORIGIN et c inclus.
	 * Sinon renvoie false.
	 */
	public boolean isInfTo(final Coord c) {
		return row * c.row >= 0 && col * c.col >= 0
			&& Math.abs(row) <= Math.abs(c.row)
			&& Math.abs(col) <= Math.abs(c.col);
	}
	
	/**
	 * Renvoie true si this se trouve dans le rectangle défini par c1 et c2
	 * Sinon renvoie false.
	 */
	public boolean isInRect(final Coord c1, final Coord c2) {
		return this.minus(c1).isInfTo(c2.minus(c1));
	}

	public boolean equals(Object obj) {
		if (obj != null && this.getClass() == obj.getClass()) {
			final Coord c = (Coord) obj;
			return row == c.row && col == c.col;
		}
		return false;
	}

	public int hashCode() {
		return 7 * row + col;
	}
	
	// CREATEURS
	/**
	 * Addition de deux coordonnées.
	 * @post
	 * 	return.equals(Coord(this.row + c.row, this.col + c.col))
	 */
	public Coord add(final Coord c) {
		return new Coord(row + c.row, col + c.col);
	}
	
	/**
	 * Renvoie l'opposé de this.
	 * @post
	 * 	return.equals(Coord(-this.row, -this.col))
	 */
	public Coord minus() {
		return new Coord(-row, -col);
	}

	/**
	 * Soustraction de deux coordonnées.
	 * @post
	 * 	return.equals(Coord(this.row - c.row, this.col - c.col))
	 */
	public Coord minus(final Coord c) {
		return new Coord(row - c.row, col - c.col);
	}

	/**
	 * Multiplication d'une coordonnée par un entier.
	 * @post
	 * 	return.equals(Coord(n * this.row, n * this.col))
	 */
	public Coord mult(int n) {
		return new Coord(n * row, n * col);
	}
	
	/**
	 * Sucre syntaxique.
	 * A utiliser dans un Iterator par exemple pour parcourir un axe.
	 */
	public Coord left() { return this.add(LEFT); }
	public Coord right() { return this.add(RIGHT); }
	public Coord up() { return this.add(UP); }
	public Coord down() { return this.add(DOWN); }
	public Coord upLeft() { return this.add(L_UP); }
	public Coord downLeft() { return this.add(L_DOWN); }
	public Coord upRight() { return this.add(R_UP); }
	public Coord downRight() { return this.add(R_DOWN); }
	
}
