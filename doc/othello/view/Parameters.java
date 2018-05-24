package othello.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

/**
 * Classe abstraite servant de super classes aux spécialisation de paramètres
 *
 */
public abstract class Parameters extends JFrame {
	private int niveauDispo = 0;
	private JFrame parent;
	private Bouton play = new Bouton("Jouer !");
	private JPanel content = new JPanel();
	private JPanel panel = new JPanel();
	private GridBagConstraints constraint = new GridBagConstraints();
	private int espace = 10;
	
	//Composant J1
	private JLabel labelStratJ1 = new JLabel("Stratégie utilisée ");
	private JComboBox<String> stratJ1 = new JComboBox<String>();
	private JLabel labelNiveauJ1 = new JLabel("Niveau ");
	private JSlider niveauJ1 = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
	
	/**
	 * Constructeur de la classe, initialise la fenêtre générale
	 * @param p : fenetre créatrice du paramètre
	 */
	public Parameters(JFrame p) {
		parent = p;

		//initialisation de la fenetre
		this.setTitle("Paramètres - Othello");
		
		//Centrer
		this.setLocationRelativeTo(null);
		//termine proc quand croix rouge
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		//annule la modification de taille
		this.setResizable(false);
	
		//init du layout du panel
		panel.setLayout(new GridBagLayout());
		panel.setOpaque(true);
		
		this.getContentPane().setBackground(Color.white);
		content.setBackground(Color.WHITE);
		panel.setBackground(Color.WHITE);
		content.setBorder(new EmptyBorder(10, 20, 10, 20));
		content.setLayout(new BorderLayout());
		content.add(panel, BorderLayout.CENTER);
		this.getContentPane().add(content);
		
		this.setVisible(true);
	}
	
	/**
	 * initialise les composants de la fenêtre
	 */
	public void init() {
		niveauJ1.setOpaque(false);
		stratJ1.addItem("AlphaBeta");
		stratJ1.addItem("SSS*");
		
		//Position titre Joueur 1
		setPosition(0, niveauDispo, 3);
		panel.add(new JLabel("Paramètre IA"), constraint);
		niveauDispo++;
		
		setAlignLeft();
		setMargeX(50);
		//Position du label de la liste dÃ©roulante de stratÃ©gie
		setPosition(0,niveauDispo,1);
		panel.add(labelStratJ1, constraint);

		setAlignMiddle();
		setMargeX(0);
		//Position de la liste dÃ©roulante de stratÃ©gie
		setPosition(1,niveauDispo,2);
		panel.add(stratJ1, constraint);
		niveauDispo++;

		setAlignLeft();
		setMargeX(50);
		//Position du label de la liste dÃ©roulante de stratÃ©gie
		setPosition(0,niveauDispo,1);
		panel.add(labelNiveauJ1, constraint);

		setAlignMiddle();
		setMargeX(0);
		//Position du slider
		setPosition(1,niveauDispo,2);
		panel.add(niveauJ1, constraint);
		niveauDispo++;
	}
	
	/**
	 * permet de modifier la position d'un composant
	 * @param x : position en x
	 * @param y : position en y
	 */
	public void setPosition(int x, int y) {
		constraint.gridx = x;
		constraint.gridy = y;
	}

	/**
	 * permet de modifier la position d'un composant
	 * @param x : position en x
	 * @param y : position en y
	 * @param tailleLargeur : taille prise par le composant en largeur
	 */
	public void setPosition(int x, int y, int tailleLargeur) {
		constraint.gridx = x;
		constraint.gridy = y;
		constraint.gridwidth = tailleLargeur;
	}

	/**
	 * permet de modifier la position d'un composant
	 * @param x : position en x
	 * @param y : position en y
	 * @param tailleLargeur : taille prise par le composant en largeur
	 * @param tailleHauteur : taille prise par le composant en hauteur
	 */
	public void setPosition(int x, int y, int tailleLargeur, int tailleHauteur) {
		constraint.gridx = x;
		constraint.gridy = y;
		constraint.gridwidth = tailleLargeur;
		constraint.gridheight = tailleHauteur;
	}
	
	/**
	 * Ajoute une marge sur l'axe des X
	 * @param x : taille de la marge
	 */
	public void setMargeX(int x) {
		constraint.insets = new Insets(espace,espace,espace,x);
	}
	
	/**
	 * Aligne a gauche les composants
	 */
	public void setAlignLeft() {
		constraint.anchor = GridBagConstraints.WEST;
	}
	
	/**
	 * Aligne à droite les composants 
	 */
	public void setAlignRight() {
		constraint.anchor = GridBagConstraints.EAST;
	}
	
	/**
	 * Centre les composants
	 */
	public void setAlignMiddle() {
		constraint.anchor = GridBagConstraints.CENTER;
	}
	
	/*
	 * GET & SET
	 */
	public JFrame getParent() {
		return parent;
	}
	
	public GridBagConstraints getConstraint() {
		return constraint;
	}

	public JPanel getPanel() {
		return panel;
	}
	
	public JButton getPlay() {
		return play;
	}
	
	public int getNiveauDispo() {
		return niveauDispo;
	}
	
	public void incrNiveauDispo() {
		niveauDispo++;
	}
	
	public String getStrat() {
		return stratJ1.getSelectedItem().toString();
	}
	
	public int getNiveau() {
		return niveauJ1.getValue();
	}
	
	/**
	 * ferme la fenetre
	 */
	public void quitter() {
		this.dispose();
	}
}
