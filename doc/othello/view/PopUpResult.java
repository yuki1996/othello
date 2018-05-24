package othello.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import othello.model.IOthello;

public class PopUpResult extends JFrame{
	//private JPanel panel;
	private Bouton restart = new Bouton("Recommencer");
	private Bouton newGame = new Bouton("Nouvelle partie");
	private Bouton quit = new Bouton("Quitter");
	private IOthello model;
	
	public PopUpResult(IOthello o) {
		model = o;
		this.setTitle("Résultat - Othello");
		this.setPreferredSize(new Dimension(300,300));
		//this.getContentPane().setBackground(new Color(1,137,42));
		
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
		JLabel winner = null;
		othello.util.Color colorWinner = o.isWinner();
		if (colorWinner != null) {
			winner = new JLabel("Le joueur " +colorToString(colorWinner) +" gagne", JLabel.CENTER);
		} else {
			winner = new JLabel("Match nul", JLabel.CENTER);
		}
		winner.setFont(new Font("Serif", Font.BOLD, 22));
		panel.add(winner);
		JPanel panelPoint = new JPanel();
		GridLayout gl2 = new GridLayout(1,3);
		panelPoint.setLayout(gl2);
		String blackResult = "Noir ";
		if (colorWinner == othello.util.Color.BLACK) {
			blackResult += (o.getBoard().getPointsPlayer(othello.util.Color.BLACK) + o.getBoard().getPointsPlayer(null));
		} else {
			blackResult += (o.getBoard().getPointsPlayer(othello.util.Color.BLACK));
		}
		panelPoint.add(new JLabel(blackResult, JLabel.CENTER));
		/*
		ImagePanel black = new ImagePanel("jeton_noir.png");
		ImagePanel white = new ImagePanel("jeton_blanc.png");
		black.setPreferredSize(new Dimension(10,10));
		white.setPreferredSize(new Dimension(10,10));
		
		panelPoint.add(black);
		*/
		panelPoint.add(new JLabel(" vs ", JLabel.CENTER));
		//panelPoint.add(white);

		String whiteResult = "Blanc ";
		if (colorWinner == othello.util.Color.WHITE) {
			whiteResult += (o.getBoard().getPointsPlayer(othello.util.Color.WHITE) + o.getBoard().getPointsPlayer(null));
		} else {
			whiteResult += (o.getBoard().getPointsPlayer(othello.util.Color.WHITE));
		}
		panelPoint.add(new JLabel(whiteResult, JLabel.CENTER));
		
		panel.add(panelPoint);
		panel.add(restart);
		panel.add(newGame);
		panel.add(quit);
		panel.setPreferredSize(new Dimension(150, 100));
		panel.setBackground(Color.WHITE);
		panel.setBorder(new EmptyBorder(10, 20, 10, 20));
		
		this.add(panel);

		initButtons();
		pack();
		this.setVisible(true);
	}
	
	private void initButtons() {
		
		restart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				model.restart();
				BoardView board = new BoardView(model);
				board.display();
				quitter();
			}
		});
		
		newGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				Menu menu = new Menu();
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
	
	private String colorToString(othello.util.Color c) {
    	if (c == othello.util.Color.BLACK) {
    		return "NOIR";
    	} else {
    		return "BLANC";
    	}
    }
}
