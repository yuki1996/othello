package othello.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class Tutorial {
	
	// ATTRIBUTS
	private JFrame mainFrame;
	
	// CONSTRUCTEURS
	public Tutorial() {
		createView();
		placeComponents();
		createController();
	}
	
	// COMMANDES
	public void display() {
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }
	
	// OUTILS  
	private void createView() {
		final int frameWidth = 500;
        final int frameHeight = 500;
         
        mainFrame = new JFrame("Aide");
        mainFrame.setPreferredSize(new Dimension(frameWidth, frameHeight));
        mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	private void placeComponents() {		
		JPanel p = new JPanel(new GridLayout(1, 1)); {
			JTextArea area = new JTextArea(); 
			
			area.append("Othello est un jeu de stratégie à deux joueurs : Noir et Blanc. Il se joue sur un plateau \n"
					+ "unicolore de 64 cases, 8 sur 8, appelé othellier. Ces joueurs disposent de 64 pions \n"
					+ "bicolores, noir d'un côté et blanc de l'autre. \n"
					+ "Par commodité, chaque joueur a devant lui 32 pions mais ils ne lui appartiennent pas \n"
					+ "et il doit en donner à son adversaire si celui-ci n'en a plus. Un pion est noir si sa face \n"
					+ "noire est visible et blanc si sa face blanche est sur le dessus.\n"
					+ "Règles du jeu :\n"  
					+ "Position de départ : au début de la partie, deux pions noirs sont placés en e4 et d5 et \n"
					+ "deux pions blancs sont placés en d4 et e5. Le joueur de couleur noire commence \n"
					+ "toujours et les deux adversaires jouent ensuite à tour de rôle.\n"
					+ "La pose d’un pion : à son tour de jeu, le joueur doit poser un pion de sa couleur sur \n"
					+ "une case vide de l’othellier, adjacente à un pion adverse (une des cases avec un  \n"
					+ "carré rouge). Il doit également, en posant son pion, encadrer un ou plusieurs pions  \n"
					+ "adverses entre le pion qu’il pose et un pion à sa couleur, déjà placé sur l’othellier.  \n"
					+ "Il retourne alors de sa couleur le ou les pions qu’il vient d’encadrer. Les pions ne  \n"
					+ "sont ni retirés de l’othellier, ni déplacés d’une case à l’autre.\n"
					+ "On peut encadrer des pions adverses dans les huit directions. Par ailleurs, dans \n"
					+ "chaque direction, plusieurs pions peuvent être encadrés. On doit alors tous les \n"
					+ "retourner.\n"
					+ "Fin de la partie : la partie est terminée lorsque aucun des deux joueurs ne peut plus \n"
					+ "jouer. Cela arrive généralement lorsque les 64 cases sont occupées. Mais il se peut \n"
					+ "qu’il reste des cases vides où personne ne peut plus jouer : par exemple lorsque \n"
					+ "tous les pions deviennent d’une même couleur après un retournement.\n"
					);
			
			
			area.setEditable(false);
			p.add(area);
			p.setPreferredSize(new Dimension(400, 400));
			area.setMargin(new Insets(10, 10, 10, 10));
		}
		mainFrame.add(p, BorderLayout.CENTER);
	}

	private void createController() {
		mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
       
	}
  
	// LANCEUR
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Tutorial().display();
            }
        });
	}
}
