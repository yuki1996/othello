package othello.view;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;

/**
 * Classe permettant de changer l'apparence d'un bouton
 *
 */
public class Bouton extends JButton{
	/**
	 * Constructeur de la classe Bouton
	 * @param s : texte afficher sur le bouton
	 */
	public Bouton(String s) {
		this.setText(s);
		this.setBackground(Color.gray);
		this.setForeground(Color.black);
	}
}
