package othello.model;

import java.util.HashSet;
import java.util.Set;

import othello.util.Color;
import othello.util.Coord;

public class Board implements IBoard{
	
	private int size;

	private Color coord_color[][]; //= new Color[getSize()][getSize()];
	
	Board(int size){
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
		Set<Coord> set = new HashSet<Coord>();
		
		//couleur adversaire;
		Color foeColor = player_color == Color.WHITE ? Color.BLACK : Color.WHITE;
		
		//on teste toute les cellules si elle peut être saisie
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				Coord xy = new Coord(i, j);
				
				//cellule n'est pas déjà prise
				if (getColor(xy) == null) {
					boolean boolAdd = false;
					
					//Cellule du haut
					Coord xyUp = xy.up();
					//vérifie que la coordonnée au dessus est valide et de la couleur de l'adversaire
					if (isValid(xyUp) && getColor(xyUp) == foeColor) {
						//on cherche un cellule où on peut entourer les cellules de l'adversaire
						xyUp = xyUp.up();
						while (isValid(xyUp) && !boolAdd) {
							if (getColor(xyUp) == player_color) {
								set.add(xy);
								boolAdd = true;
							}
							xyUp = xyUp.up();
						}
					}
					
					//Cellule du haut à droite
					Coord xyUpRight = xy.upRight();
					//vérifie qu'on a pas déjà ajouté la cellule, la coordonnée en haut à droite est valide et de la couleur de l'adversaire
					if (!boolAdd && isValid(xyUpRight) && getColor(xyUpRight) == foeColor) {
						//on cherche un cellule où on peut entourer les cellules de l'adversaire
						xyUpRight = xyUpRight.upRight();
						while (isValid(xyUpRight) && !boolAdd) {
							if (getColor(xyUpRight) == player_color) {
								set.add(xy);
								boolAdd = true;
							}
							xyUpRight = xyUpRight.upRight();
						}
					}
					
					//Cellule à droite
					Coord xyRight = xy.right();
					//vérifie qu'on a pas déjà ajouté la cellule, la coordonnée à droite est valide et de la couleur de l'adversaire
					if (!boolAdd && isValid(xyRight) && getColor(xyRight) == foeColor) {
						//on cherche un cellule où on peut entourer les cellules de l'adversaire
						xyRight = xyRight.right();
						while (isValid(xyRight) && !boolAdd) {
							if (getColor(xyRight) == player_color) {
								set.add(xy);
								boolAdd = true;
							}
							xyRight = xyRight.right();
						}
					}
					
					//Cellule en bas à droite
					Coord xyDownRight = xy.downRight();
					//vérifie qu'on a pas déjà ajouté la cellule, la coordonnée en bas à droite est valide et de la couleur de l'adversaire
					if (!boolAdd && isValid(xyDownRight) && getColor(xyDownRight) == foeColor) {
						//on cherche un cellule où on peut entourer les cellules de l'adversaire
						xyDownRight = xyDownRight.downRight();
						while (isValid(xyDownRight) && !boolAdd) {
							if (getColor(xyDownRight) == player_color) {
								set.add(xy);
								boolAdd = true;
							}
							xyDownRight = xyDownRight.downRight();
						}
					}
					
					//Cellule en bas
					Coord xyDown = xy.down();
					//vérifie qu'on a pas déjà ajouté la cellule, la coordonnée en bas est valide et de la couleur de l'adversaire
					if (!boolAdd && isValid(xyDown) && getColor(xyDown) == foeColor) {
						//on cherche un cellule où on peut entourer les cellules de l'adversaire
						xyDown = xyDown.down();
						while (isValid(xyDown) && !boolAdd) {
							if (getColor(xyDown) == player_color) {
								set.add(xy);
								boolAdd = true;
							}
							xyDown = xyDown.down();
						}
					}
					
					//Cellule en bas à gauche
					Coord xyDownLeft = xy.downLeft();
					//vérifie qu'on a pas déjà ajouté la cellule, la coordonnée en bas à gauche est valide et de la couleur de l'adversaire
					if (!boolAdd && isValid(xyDownLeft) && getColor(xyDownLeft) == foeColor) {
						//on cherche un cellule où on peut entourer les cellules de l'adversaire
						xyDownLeft = xyDownLeft.downLeft();
						while (isValid(xyDownLeft) && !boolAdd) {
							if (getColor(xyDownLeft) == player_color) {
								set.add(xy);
								boolAdd = true;
							}
							xyDownLeft = xyDownLeft.downLeft();
						}
					}
	
					//Cellule à gauche
					Coord xyLeft = xy.left();
					//vérifie qu'on a pas déjà ajouté la cellule, la coordonnée à gauche est valide et de la couleur de l'adversaire
					if (!boolAdd && isValid(xyLeft) && getColor(xyLeft) == foeColor) {
						//on cherche un cellule où on peut entourer les cellules de l'adversaire
						xyLeft = xyLeft.left();
	
						while (isValid(xyLeft) && !boolAdd) {
							if (getColor(xyLeft) == player_color) {
								set.add(xy);
								boolAdd = true;
							}
							xyLeft = xyLeft.left();
						}
					}
					
					//Cellule en haut à gauche
					Coord xyUpLeft = xy.upLeft();
					//vérifie qu'on a pas déjà ajouté la cellule, la coordonnée en haut à gauche est valide et de la couleur de l'adversaire
					if (!boolAdd && isValid(xyUpLeft) && getColor(xyUpLeft) == foeColor) {
						//on cherche un cellule où on peut entourer les cellules de l'adversaire
						xyUpLeft = xyUpLeft.upLeft();
						while (isValid(xyUpLeft) && !boolAdd) {
							if (getColor(xyUpLeft) == player_color) {
								set.add(xy);
								boolAdd = true;
							}
							xyUpLeft = xyUpLeft.upLeft();
						}
					}
				}
			}
		}
		return set;
	}
	
	public boolean isValid(Coord xy) {
		if (0 <= xy.row() && xy.row() < getSize() 
			&& 0 <= xy.col() && xy.col() < getSize()) {
			return true;
		}
		return false;
	}
	
	//METHODES
	public void putDisk(Coord xy, Color color) {
		if (!isValid(xy)) {
			throw new IllegalArgumentException("mouvement impossible");
		}
		coord_color[xy.row()][xy.col()] = color;
	}
	
}