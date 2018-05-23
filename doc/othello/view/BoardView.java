package othello.view;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import othello.model.IOthello;
import othello.model.Othello;
import othello.util.Color;
import othello.util.Coord;

public class BoardView {

	// ATTRIBUTS

    public final int BORDER_SIZE = 1;
	private static final Dimension DEFAULT_PREFERED_DIMENSION = new Dimension(30,30);
	private static final Color BORDER_COLOR = Color.BLACK;
	
    private IOthello model;
    private CellView[][] cells;
    private JFrame mainFrame;
    private JLabel currentPlayer;
    private JLabel whiteScore;
    private JLabel whitePlayer;
    private JLabel blackPlayer;
    private JLabel blackScore;
    
    // CONSTRUCTEUR
    public BoardView(IOthello model) {
        createModel(model);
        createView();
        placeComponents();
        createController();
    }
    
    // REQUETES
    public IOthello getModel() {
        return model;
    }
    
    // COMMANDES
    
    public void display() {
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }
    
    public void setModel(IOthello model) {
        this.model = model;
        refreshBoard();
        refreshPlayer();
    }
    
    //OUTILS
    
    private void createModel(IOthello model) {
        this.model = model;
    }
    
    private void createView() {
        cells = new CellView[model.getBoard().getSize()][model.getBoard().getSize()];
        Color colorCurrentPlayer = getModel().getCurrentPlayer().getColor();
        Set<Coord> validMoves = getModel().getBoard().getValidMoves(colorCurrentPlayer);
        for(int i = 0 ; i < cells.length; ++i) {
        	for(int j = 0 ; j < cells[i].length; ++j) {
        		Coord coord = new Coord(i,j);
        		Color c = getModel().getBoard().getColor(coord);
        		if (c == Color.BLACK) {
                    cells[i][j] = new CellView(DrawableCell.BLACK);
        		} else if (c == Color.WHITE) {
                    cells[i][j] = new CellView(DrawableCell.WHITE);
        		} else if (validMoves.contains(coord)) {
                    cells[i][j] = new CellView(DrawableCell.VALID_MOVE);
        		} else {
                    cells[i][j] = new CellView(DrawableCell.INVALID_MOVE);
        		}
            }
        }
        mainFrame = new JFrame("Plateau de jeu");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setResizable(false);
        currentPlayer = new JLabel("Joueur " 
        		+ colorToString(model.getCurrentPlayer().getColor()) + " doit jouer.");
        whiteScore = new JLabel(model.getBoard().getPointsPlayer(Color.WHITE) + "");
        whitePlayer = new JLabel(colorToString(Color.WHITE) + " : ");
        
        blackScore = new JLabel(model.getBoard().getPointsPlayer(Color.BLACK) + "");
        blackPlayer = new JLabel(colorToString(Color.BLACK) + " : ");
    }
    
    private void placeComponents() {
    	JPanel row = new JPanel(new GridLayout(model.getBoard().getSize(), 1)); {
			for(int i = 0 ; i < cells.length; ++i) {
				JLabel headerText = new JLabel((i +  1) +"", JLabel.CENTER);
				headerText.setForeground(java.awt.Color.WHITE);
				JPanel header = new JPanel();
				header.setPreferredSize(DEFAULT_PREFERED_DIMENSION);
				header.setBackground(new java.awt.Color(139,69,19));
				header.setBorder(BorderFactory.createLineBorder(java.awt.Color.BLACK, BORDER_SIZE));
				header.add(headerText);
				row.add(header);
	        }
		}
        JPanel p = new JPanel(new GridLayout(model.getBoard().getSize(), 
        		model.getBoard().getSize())); {
			for(int i = 0 ; i < cells.length; ++i) {
	        	for(int j = 0 ; j < cells[i].length; ++j) {
	            	p.add(cells[i][j]);
	            }
	        }
		}
		JPanel col = new JPanel(new GridLayout(1, model.getBoard().getSize())); {
			for(int i = 0 ; i < cells.length; ++i) {
				JLabel headerText = new JLabel((char) ('a' + i) + "", JLabel.CENTER);
				headerText.setForeground(java.awt.Color.WHITE);
				JPanel header = new JPanel();
				header.setPreferredSize(DEFAULT_PREFERED_DIMENSION);
				header.setBackground(new java.awt.Color(139,69,19));
				header.setBorder(BorderFactory.createLineBorder(java.awt.Color.BLACK, BORDER_SIZE));
				header.add(headerText);
				col.add(header);
	        }
		}
		
		JPanel board = new JPanel();
        board.setLayout(new GridBagLayout());
	    GridBagConstraints gbc = new GridBagConstraints();
			
	    gbc.gridx = 0;
	    gbc.gridy = 0;
	    gbc.gridheight = 1;
	    gbc.gridwidth = 1;
	    board.add(new JPanel(), gbc);
	    
	    gbc.gridx = 1;
        board.add(col, gbc);
        
        gbc.gridx = 0;
	    gbc.gridy = 1;
        board.add(row, gbc);
        
        gbc.gridx = 1;
        board.add(p, gbc);
        mainFrame.add(board, BorderLayout.CENTER);
        p = new JPanel(new GridLayout(2,1)); {
        	JPanel q = new JPanel(new FlowLayout()); {
        		p.add(new ImagePanel("jeton_blanc.png"));
        		p.add(whitePlayer);
            	p.add(whiteScore);
        	}
        	p.add(q);
        	q = new JPanel(new FlowLayout()); {
        		p.add(new ImagePanel("jeton_noir.png"));
        		p.add(blackPlayer);
            	p.add(blackScore);
        	}
        	p.add(q);
		}
        mainFrame.add(p, BorderLayout.EAST);
        mainFrame.add(currentPlayer, BorderLayout.NORTH);
    }
    
    private void createController() {
    	for(int i = 0 ; i < cells.length; ++i) {
        	for(int j = 0 ; j < cells[i].length; ++j) {
            	cells[i][j].addMouseListener(new CellListener(model, i, j));
            }
        }

       model.addPropertyChangeListener(IOthello.TURN, new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				refreshPlayer();
				refreshBoard();
			}
		});
    }
    
    private void refreshBoard() {
	    Color colorCurrentPlayer = getModel().getCurrentPlayer().getColor();
        Set<Coord> validMoves = getModel().getBoard().getValidMoves(colorCurrentPlayer);
	    for(int i = 0 ; i < cells.length; ++i) {
	    	for(int j = 0 ; j < cells[i].length; ++j) {
	    		Coord coord = new Coord(i,j);
	    		Color c = getModel().getBoard().getColor(coord);
	    		if (c == Color.BLACK) {
	                cells[i][j].setDrawableCell(DrawableCell.BLACK);
	    		} else if (c == Color.WHITE) {
	                cells[i][j].setDrawableCell(DrawableCell.WHITE);
	    		} else if (validMoves.contains(coord)) {
	                cells[i][j].setDrawableCell(DrawableCell.VALID_MOVE);
	    		} else {
	                cells[i][j].setDrawableCell(DrawableCell.INVALID_MOVE);
	    		}
			}
	    }
    	whiteScore.setText(model.getBoard().getPointsPlayer(Color.WHITE) + "");
        blackScore.setText(model.getBoard().getPointsPlayer(Color.BLACK) + "");
    }
    
    private void refreshPlayer() {
    	currentPlayer.setText("Joueur " 
        		+ colorToString(model.getCurrentPlayer().getColor()) + " doit jouer.");
    	if (model.isGameOver()) {
			//TODO
		}
    }
    
    private String colorToString(Color c) {
    	if (c == Color.BLACK) {
    		return "NOIR";
    	} else {
    		return "BLANC";
    	}
    }
    
    //CLASSES INTERNES
    class CellListener extends MouseAdapter {
    	
    	private IOthello model;
    	private int i;
    	private int j;
    	
    	public CellListener(IOthello model, int i, int j) {
    		this.model = model;
    		this.i = i;
    		this.j = j;
    	}
    	
    	public void mouseClicked(MouseEvent e) {
    		Color colorCurrentPlayer = getModel().getCurrentPlayer().getColor();
    	    Set<Coord> validMoves = getModel().getBoard().getValidMoves(colorCurrentPlayer);
    	    Coord coord = new Coord(i,j);
    		if (model.canPlay(model.getCurrentPlayer()) && validMoves.contains(coord)) {
    			model.turn(coord);
    		}
    	}
    }

    
    // TEST
    public static void main(String[] args) {
    	IOthello model = new Othello();
        BoardView bv = new BoardView(model);
        bv.display();
    }
}
