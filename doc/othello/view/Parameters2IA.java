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
		
		stratJ2.addItem("Stratégie 1");
		stratJ2.addItem("Stratégie 2");
		
		//Position titre Joueur 1
		setPosition(0, getNiveauDispo(), 3);
		getPanel().add(new JLabel("Paramètre IA 2"), getConstraint());
		incrNiveauDispo();
		
		//Position du label de la liste déroulante de stratégie
		setPosition(0,getNiveauDispo(),1);
		getPanel().add(labelStratJ2, getConstraint());
		
		//Position de la liste déroulante de stratégie
		setPosition(1,getNiveauDispo(),2);
		getPanel().add(stratJ2, getConstraint());
		incrNiveauDispo();
		
		//Position du label de la liste déroulante de stratégie
		setPosition(0,getNiveauDispo(),1);
		getPanel().add(labelNiveauJ2, getConstraint());
		
		//Position du slider
		setPosition(1,getNiveauDispo(),2);
		getPanel().add(niveauJ2, getConstraint());
		incrNiveauDispo();
		
		//init bouton play
		getPlay().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				BoardView board = new BoardView(new Othello(getStrat(), getNiveau(), stratJ2.getSelectedItem().toString(), niveauJ2.getValue()));
			}
		});

		super.pack();
	}
}
