package othello.view;


import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import othello.model.IOthello;
import othello.model.IPlayer;
import othello.model.Othello;
import othello.util.Color;
import othello.util.Coord;

public class BoardView {

	// ATTRIBUTS

    public final int BORDER_SIZE = 1;
    
    private IOthello model;
    private CellView[][] cells;
    private JFrame mainFrame;
    private JLabel currentPlayer;
    private JLabel whiteDiskNb;
    private JLabel blackDiskNb;
    
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
        Color colorCurrentPlayer = getModel().getCurrentPlayer().getColor();
        for(int i = 0 ; i < cells.length; ++i) {
        	for(int j = 0 ; j < cells[i].length; ++j) {
        		Coord coord = new Coord(i,j);
        		Color c = getModel().getBoard().getColor(coord);
        		if (c == Color.BLACK) {
                    cells[i][j].setDrawableCell(DrawableCell.BLACK);
        		} else if (c == Color.WHITE) {
                    cells[i][j].setDrawableCell(DrawableCell.WHITE);
        		} else if (getModel().getValidMoves(colorCurrentPlayer).contains(coord)) {
                    cells[i][j].setDrawableCell(DrawableCell.VALID_MOVE);
        		} else {
                    cells[i][j].setDrawableCell(DrawableCell.INVALID_MOVE);
        		}
            }
        }
    }
    
    private void createModel(IOthello model) {
        this.model = model;
    }
    
    private void createView() {
        cells = new CellView[model.getBoard().getSize()][model.getBoard().getSize()];
        Color colorCurrentPlayer = getModel().getCurrentPlayer().getColor();
        for(int i = 0 ; i < cells.length; ++i) {
        	for(int j = 0 ; j < cells[i].length; ++j) {
        		Coord coord = new Coord(i,j);
        		Color c = getModel().getBoard().getColor(coord);
        		if (c == Color.BLACK) {
                    cells[i][j] = new CellView(DrawableCell.BLACK);
        		} else if (c == Color.WHITE) {
                    cells[i][j] = new CellView(DrawableCell.WHITE);
        		} else if (getModel().getValidMoves(colorCurrentPlayer).contains(coord)) {
                    cells[i][j] = new CellView(DrawableCell.VALID_MOVE);
        		} else {
                    cells[i][j] = new CellView(DrawableCell.INVALID_MOVE);
        		}
            }
        }
        mainFrame = new JFrame("Plateau de jeu");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setResizable(false);
        currentPlayer = new JLabel("C'est le tour du joueur " 
        		+ getPlayerName(model.getCurrentPlayer()) + ".");
        whiteDiskNb = new JLabel("salut");
        blackDiskNb = new JLabel("salut");
    }
    
    private void placeComponents() {
    	JPanel p = new JPanel(new GridLayout(model.getBoard().getSize(), 
        		model.getBoard().getSize())); {
			for(int i = 0 ; i < cells.length; ++i) {
	        	for(int j = 0 ; j < cells[i].length; ++j) {
	            	p.add(cells[i][j]);
	            }
	        }
		}
        mainFrame.add(p, BorderLayout.CENTER);
        p = new JPanel(new BorderLayout()); {
	        JPanel q = new JPanel(new GridLayout(2,1)); {
	        	q.add(blackDiskNb);
	        	q.add(whiteDiskNb);
			}
	        p.add(q, BorderLayout.NORTH);
        }
        mainFrame.add(p, BorderLayout.EAST);
        mainFrame.add(currentPlayer, BorderLayout.NORTH);
    }
    
    private void createController() {
//        PropertyChangeListener cellListener = new PropertyChangeListener() {
//
//            @Override
//            public void propertyChange(PropertyChangeEvent evt) {
//                CellModel c = ((Cell) evt.getSource()).getModel();
//                GridModel g = model.getGridPlayer();
//                Command cmd = null;
//                String propertyName = evt.getPropertyName();
//                int newValue = (Integer) evt.getNewValue();
//                if (propertyName.equals(CellModel.VALUE)) {
//                    if (newValue == 0) {
//                        cmd = new RemoveValue(g, c);
//                    } else {
//                        cmd = new AddValue(g, c, newValue);
//                    }
//                } else if (propertyName.equals(CellModel.CANDIDATE)) {
//                    if (c.isCandidate(newValue)) {
//                        cmd = new RemoveCandidate(g, c, newValue);
//                    } else {
//                        cmd = new AddCandidate(g, c, newValue);
//                    }
//                }
//                model.act(cmd);
//            }
//            
//        };
//        
//        for (Cell[] ctab : cells) {
//            for (Cell c : ctab) {
//                c.addPropertyChangeListener(CellModel.CANDIDATE, cellListener);
//                c.addPropertyChangeListener(CellModel.VALUE, cellListener);
//            }
//        }
//        
//        model.addPropertyChangeListener(SudokuModel.GRID,
//        		new PropertyChangeListener() {
//
//					@Override
//					public void propertyChange(PropertyChangeEvent evt) {
//						setModel(model);
//					}
//        	
//        });
//        
//        model.addPropertyChangeListener(RuleManager.LAST_REPORT,
//        		new PropertyChangeListener() {
//
//			@Override
//			public void propertyChange(PropertyChangeEvent evt) {
//				boolean paint = evt.getOldValue() == null;
//				highlightCells(((Report) (paint ? evt.getNewValue()
//					for : evt.getOldValue())).importantSets(), paint);
//			}
//        	
//        });
    }
    
    private String getPlayerName(IPlayer player) {
    	return player.getColor().name();
    }
    
    // TEST
    public static void main(String[] args) {
    	IOthello model = new Othello();
        BoardView bv = new BoardView(model);
        bv.display();
        bv.cells[0][0].setDrawableCell(DrawableCell.BLACK);
        bv.cells[0][1].setDrawableCell(DrawableCell.WHITE);
        bv.cells[0][2].setDrawableCell(DrawableCell.VALID_MOVE);
    }
    
}
