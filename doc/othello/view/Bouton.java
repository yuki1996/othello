package othello.view;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

public class Bouton extends JButton{
	public Bouton(String s) {
		this.setText(s);
		this.setBackground(new Color(98,56,0));
		this.setForeground(Color.white);
		
	}
}
