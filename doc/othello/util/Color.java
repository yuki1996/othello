package othello.util;

/**
 * Modélise une couleur. 
 * 
 * une case vide ou un match nul sera représenté par null
 */
public enum Color {
	BLACK{
		public String toString() {
			return "NOIR";
		}
	},
	WHITE {
		public String toString() {
			return "BLANC";
		}
	};
}