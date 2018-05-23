package othello.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import othello.model.Othello;

public class ParametersHvsIA extends Parameters {
	private JLabel labelJeton = new JLabel("Choix couleur ");
	private JPanel black = new JPanel();
	private JPanel white = new JPanel();
	private othello.util.Color selected = othello.util.Color.WHITE;
	
	public ParametersHvsIA(JFrame p) {
		super(p);
		init();		
		initJetons();
		
		black.setPreferredSize(new Dimension(50,50));
		white.setPreferredSize(new Dimension(50,50));
		
		//Position label Joueur
		setPosition(0, getNiveauDispo(), 3);
		getPanel().add(new JLabel("Joueur"), getConstraint());
		incrNiveauDispo();
		
		//Position label jeton
		setPosition(0, getNiveauDispo(), 1);
		getPanel().add(labelJeton, getConstraint());
		
		//Position jeton noir
		setPosition(1, getNiveauDispo(), 1);
		getPanel().add(black, getConstraint());
		
		//Position jeton blanc
		setPosition(2, getNiveauDispo(), 1);
		getPanel().add(white, getConstraint());
		incrNiveauDispo();
		
		//Position bouton play
		setPosition(0, getNiveauDispo(), 3);
		getPanel().add(getPlay(), getConstraint());
		
		super.pack();
	}
	
	private void initSelect() {
		black.addMouseListener(new MouseAdapter() 
		{
		    @Override
		    public void mouseClicked(MouseEvent e) 
		    {
		    	addBorder(black);
		    }
		});
		white.addMouseListener(new MouseAdapter() 
		{
		    @Override
		    public void mouseClicked(MouseEvent e) 
		    {
		    	addBorder(white);
		    }
		});
	}
	
	private void addBorder(JPanel panel) {
		black.setBorder(BorderFactory.createLineBorder(Color.black));
		white.setBorder(BorderFactory.createLineBorder(Color.black));
		panel.setBorder(BorderFactory.createLineBorder(Color.red));
	}
	
	private void initJetons() {
		black = new ImagePanel("jeton_noir.png");
		white = new ImagePanel("jeton_blanc.png");
		initSelect();
		
		//init bouton play
		getPlay().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				BoardView board = new BoardView(new Othello(selected, getStrat(), getNiveau()));
				board.display();
				quitter();
			}
		});
	}
}
