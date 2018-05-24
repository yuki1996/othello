package othello.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;

import othello.model.Othello;

public class Parameters2IA extends Parameters{
	//Composant J2
	private JLabel labelStratJ2 = new JLabel("Stratégie utilisée ");
	private JComboBox<String> stratJ2 = new JComboBox<String>();
	private JLabel labelNiveauJ2 = new JLabel("Niveau ");
	private JSlider niveauJ2 = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
	
	public Parameters2IA(JFrame p) {
		super(p);
		init();
		niveauJ2.setOpaque(false);
		stratJ2.addItem("AlphaBeta");
		stratJ2.addItem("SSS*");
		
		//Position titre Joueur 1
		setPosition(0, getNiveauDispo(), 3);
		getPanel().add(new JLabel("Paramètre IA 2"), getConstraint());
		incrNiveauDispo();
		
		setAlignLeft();
		setMargeX(50);
		//Position du label de la liste déroulante de stratégie
		setPosition(0,getNiveauDispo(),1);
		getPanel().add(labelStratJ2, getConstraint());
		
		setAlignMiddle();
		setMargeX(0);
		//Position de la liste déroulante de stratégie
		setPosition(1,getNiveauDispo(),2);
		getPanel().add(stratJ2, getConstraint());
		incrNiveauDispo();
		
		setAlignLeft();
		setMargeX(50);
		//Position du label de la liste déroulante de stratégie
		setPosition(0,getNiveauDispo(),1);
		getPanel().add(labelNiveauJ2, getConstraint());
		
		setAlignMiddle();
		setMargeX(0);
		//Position du slider
		setPosition(1,getNiveauDispo(),2);
		getPanel().add(niveauJ2, getConstraint());
		incrNiveauDispo();
		
		//Position bouton play
		setPosition(0, getNiveauDispo(), 3);
		getPanel().add(getPlay(), getConstraint());
		getPanel().setOpaque(false);
		
		//init bouton play
		getPlay().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				BoardView board = new BoardView(new Othello(getStrat(), getNiveau(), stratJ2.getSelectedItem().toString(), niveauJ2.getValue()));
				board.display();
				quitter();
			}
		});

		super.pack();
	}
}
