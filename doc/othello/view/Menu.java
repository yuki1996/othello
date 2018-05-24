package othello.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import othello.model.Othello;

/**
 * Classe permettant de créer la fenêtre contenant le menu
 *
 */
public class Menu extends JFrame{
	private Bouton quit = new Bouton("Quitter");
	private Bouton gameAgainstIA = new Bouton("Joueur VS IA");
	private Bouton game2IA = new Bouton("IA VS IA");
	private Bouton game2H = new Bouton("Joueur VS Joueur");
	private Bouton tuto = new Bouton("Aide");
	
	/**
	 * Constructeur de la classe menu, ajoute les différents composants
	 */
	public Menu () {
		//initialisation des boutons
		initButtons();
		//initialisation de la fenetre
		this.setTitle("Menu - Othello");
		this.setPreferredSize(new Dimension(350,350));
		this.getContentPane().setBackground(Color.WHITE);
		
		//Centrer
		this.setLocationRelativeTo(null);
		//termine proc quand croix rouge
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		//annule la modification de taille
		this.setResizable(false);
		
		//layout des boutons
		GridLayout gl = new GridLayout(5,1);
		//espace entre les boutons
		gl.setHgap(5);
		gl.setVgap(5);
		
		//panel qui contient les boutons
		JPanel panel = new JPanel();
		panel.setLayout(gl);
		panel.add(tuto);
		panel.add(gameAgainstIA);
		panel.add(game2IA);
		panel.add(game2H);
		panel.add(quit);
		panel.setBackground(Color.WHITE);

		panel.setBorder(new EmptyBorder(30, 20, 10, 20));
		this.add(panel);
		
		pack();
		//afficher la fenetre
		this.setVisible(true);
	}

	/**
	 * Initialise les boutons et les événements associés aux clics
	 */
	private void initButtons() {
		JFrame fenetre = this;
		
		tuto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				Tutorial tuto = new Tutorial();
				tuto.display();
			}
		});
		
		game2H.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				BoardView board = new BoardView(new Othello());
				board.display();
				quitter();
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
	
	/**
	 * Ferme l'application
	 */
	private void quitter() {
		this.dispose();
	}
}
