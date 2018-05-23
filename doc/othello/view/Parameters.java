package othello.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
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

public abstract class Parameters extends JFrame {
	private int niveauDispo = 0;
	private JFrame parent;
	private Bouton play = new Bouton("Jouer !");
	private JPanel panel = new JPanel();
	private GridBagConstraints constraint = new GridBagConstraints();
	
	//Composant J1
	private JLabel labelStratJ1 = new JLabel("Stratégie utilisée ");
	private JComboBox<String> stratJ1 = new JComboBox<String>();
	private JLabel labelNiveauJ1 = new JLabel("Niveau ");
	private JSlider niveauJ1 = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
	
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
		this.getContentPane().add(panel);
		
		this.setVisible(true);
	}
	
	public void init() {
		stratJ1.addItem("Stratégie 1");
		stratJ1.addItem("Stratégie 2");
		
		//Position titre Joueur 1
		setPosition(0, niveauDispo, 3);
		panel.add(new JLabel("Paramètre IA"), constraint);
		niveauDispo++;
		
		//Position du label de la liste déroulante de strat�gie
		setPosition(0,niveauDispo,1);
		panel.add(labelStratJ1, constraint);
		
		//Position de la liste déroulante de strat�gie
		setPosition(1,niveauDispo,2);
		panel.add(stratJ1, constraint);
		niveauDispo++;
		
		//Position du label de la liste déroulante de strat�gie
		setPosition(0,niveauDispo,1);
		panel.add(labelNiveauJ1, constraint);
		
		//Position du slider
		setPosition(1,niveauDispo,2);
		panel.add(niveauJ1, constraint);
		niveauDispo++;
	}
	
	public void setPosition(int x, int y) {
		constraint.gridx = x;
		constraint.gridy = y;
	}
	
	public void setPosition(int x, int y, int tailleLargeur) {
		constraint.gridx = x;
		constraint.gridy = y;
		constraint.gridwidth = tailleLargeur;
	}
	
	public void setPosition(int x, int y, int tailleLargeur, int tailleHauteur) {
		constraint.gridx = x;
		constraint.gridy = y;
		constraint.gridwidth = tailleLargeur;
		constraint.gridheight = tailleHauteur;
	}
	
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
	
	public void quitter() {
		this.dispose();
	}
}
