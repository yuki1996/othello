package othello.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import othello.model.Othello;

public class Menu extends JFrame{
	private Bouton quit = new Bouton("Quitter");
	private Bouton gameAgainstIA = new Bouton("Joueur VS IA");
	private Bouton game2IA = new Bouton("IA VS IA");
	private Bouton game2H = new Bouton("Joueur VS Joueur");
	
	public Menu () {
		//initialisation des boutons
		initButtons();
		//initialisation de la fenetre
		this.setTitle("Menu - Othello");
		this.setSize(500,300);
		this.getContentPane().setBackground(new Color(1,137,42));
		
		//Centrer
		this.setLocationRelativeTo(null);
		//termine proc quand croix rouge
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		//annule la modification de taille
		this.setResizable(false);
		
		//layout des boutons
		GridLayout gl = new GridLayout(4,1);
		//espace entre les boutons
		gl.setHgap(5);
		gl.setVgap(5);
		
		//panel qui contient les boutons
		JPanel panel = new JPanel();
		panel.setLayout(gl);
		panel.add(gameAgainstIA);
		panel.add(game2IA);
		panel.add(game2H);
		panel.add(quit);
		panel.setPreferredSize(new Dimension(200, 100));
		panel.setBackground(new Color(1,137,42));
		
		//placement au centre du panel
		//this.getContentPane().add(panel, BorderLayout.CENTER);
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		this.add(panel);
		
		//afficher la fenetre
		this.setVisible(true);
	}

	private void initButtons() {
		JFrame fenetre = this;
		
		game2H.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				BoardView othello = new BoardView(new Othello());
				//quitter();
			}
		});
		
		gameAgainstIA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				ParametersHvsIA parametre = new ParametersHvsIA(fenetre);
				quitter();
			}
		});
		
		game2IA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				Parameters2IA parametre = new Parameters2IA(fenetre);
				quitter();
			}
		});
		
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				quitter();
			}
		});
	}
	
	private void quitter() {
		this.dispose();
	}
}
