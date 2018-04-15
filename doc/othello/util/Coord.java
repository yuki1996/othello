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
	public static final Coord L_UP = LEFT.plus(UP);
	public static final Coord L_DOWN = LEFT.plus(DOWN);
	public static final Coord R_UP = RIGHT.plus(UP);
	public static final Coord R_DOWN = RIGHT.plus(DOWN);
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
	
	public enum Axe {
		/**
		 * Axe de symétrie vertical.
		 */
		VERT {
			public Coord sym(final Coord c, final Coord rect) {
				return new Coord(c.row, rect.col - c.col);
			}
		},
		/**
		 * Axe de symétrie horizontal.
		 */
		HORIZ {
			public Coord sym(final Coord c, final Coord rect) {
				return new Coord(rect.row - c.row, c.col);
			}
		},
		/**
		 * Axe de symétrie diagonal [\].
		 */
		DIAG {
			public Coord sym(final Coord c, final Coord rect) {
				if (rect.row * rect.col < 0) {
					return new Coord(c.col, c.row).plus(rect);
				} else {
					return new Coord(c.col, c.row);
				}
			}
		},
		/**
		 * Axe de symétrie antidiagonal [/].
		 */
		ADIAG {
			public Coord sym(final Coord c, final Coord rect) {
				if (rect.row * rect.col < 0) {
					return new Coord(-c.col, -c.row);
				} else {
					return new Coord(-c.col, -c.row).plus(rect);
				}
			}
		};
		
		/**
		 * Renvoie le symétrique de c par un axe du rectangle défini par ORIGIN et rect.
		 */
		public abstract Coord sym(final Coord c, final Coord rect);

		/**
		 * Renvoie le symétrique de c par un axe du rectangle défini par rect1 et rect2.
		 */
		public Coord sym(final Coord c, final Coord rect1, final Coord rect2) {
			return sym(c.minus(rect1), rect2.minus(rect1)).plus(rect1);
		}
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
	public boolean isInRect(final Coord c) {
		return row * c.row >= 0 && col * c.col >= 0
			&& Math.abs(row) <= Math.abs(c.row)
			&& Math.abs(col) <= Math.abs(c.col);
	}
	
	/**
	 * Renvoie true si this se trouve dans le rectangle défini par c1 et c2
	 * Sinon renvoie false.
	 */
	public boolean isInRect(final Coord c1, final Coord c2) {
		return this.minus(c1).isInRect(c2.minus(c1));
	}
	
	/**
	 * Evalue la distance entre this et le centre du rectangle défini par c1 et c2.
	 */
	public double distToCenter(final Coord c1, final Coord c2) {
		return dist(c1.plus(c2).div(2), this);
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
	public Coord plus(final Coord c) {
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
	 * Multiplication d'une coordonnée par une autre coordonée.
	 * @post
	 * 	return.equals(Coord(this.row * c.row, this.col * c.col))
	 */
	public Coord mult(final Coord c) {
		return new Coord(row * c.row, col * c.col);
	}

	/**
	 * Division d'une coordonnée par un entier.
	 * Attention division entière
	 * @pre
	 * 	n != 0
	 * @post
	 * 	return.equals(Coord(this.row / n, this.col / n))
	 */
	public Coord div(int n) {
		return new Coord(row / n, col / n);
	}

	/**
	 * Sucre syntaxique.
	 * A utiliser dans un Iterator par exemple pour parcourir un axe.
	 */
	public Coord left() { return this.plus(LEFT); }
	public Coord right() { return this.plus(RIGHT); }
	public Coord up() { return this.plus(UP); }
	public Coord down() { return this.plus(DOWN); }
	public Coord upLeft() { return this.plus(L_UP); }
	public Coord downLeft() { return this.plus(L_DOWN); }
	public Coord upRight() { return this.plus(R_UP); }
	public Coord downRight() { return this.plus(R_DOWN); }

}
