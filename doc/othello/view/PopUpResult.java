package othello.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import othello.model.IOthello;

public class PopUpResult extends JFrame{
	//private JPanel panel;
	private Bouton restart = new Bouton("Recommencer");
	private Bouton newGame = new Bouton("Nouvelle partie");
	private Bouton quit = new Bouton("Quitter");
	
	public PopUpResult(IOthello o) {
		this.setTitle("Résultat - Othello");
		this.setSize(500,300);
		this.getContentPane().setBackground(new Color(1,137,42));
		
		//Centrer
		this.setLocationRelativeTo(null);
		//termine proc quand croix rouge
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		//annule la modification de taille
		//this.setResizable(false);
		
		//layout des boutons
		GridLayout gl = new GridLayout(5,1);
		//espace entre les boutons
		gl.setHgap(5);
		gl.setVgap(5);
		
		//panel qui contient les boutons
		JPanel panel = new JPanel();
		panel.setLayout(gl);
		
		if (o.isWinner() != null) {
			panel.add(new JLabel(o.isWinner()+" gagne"));
		} else {
			panel.add(new JLabel("Match nul"));
		}
		
		JPanel panelPoint = new JPanel();
		GridLayout gl2 = new GridLayout(1,5);
		panelPoint.setLayout(gl2);
		panelPoint.add(new JLabel(o.getBoard().getPointsPlayer(othello.util.Color.BLACK)+" "));
		
		ImagePanel black = new ImagePanel("jeton_noir.png");
		ImagePanel white = new ImagePanel("jeton_blanc.png");
		black.setPreferredSize(new Dimension(10,10));
		white.setPreferredSize(new Dimension(10,10));
		
		panelPoint.add(black);
		
		panelPoint.add(new JLabel(" vs "));
		panelPoint.add(new JLabel(o.getBoard().getPointsPlayer(othello.util.Color.WHITE)+" "));
		panelPoint.add(white);
		
		panel.add(panelPoint);
		panel.add(restart);
		panel.add(newGame);
		panel.add(quit);
		
		panel.setPreferredSize(new Dimension(150, 100));
		panel.setBackground(new Color(1,137,42));
		
		this.add(panel);
		pack();
		this.setVisible(true);
	}
}
