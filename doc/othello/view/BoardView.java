package othello.view;


import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import othello.model.IOthello;
import othello.model.Othello;
import othello.util.Coord;

public class BoardView extends JPanel {

	// ATTRIBUTS
	
    public final int BORDER_SIZE = 1;
    
    private IOthello model;
    
    private CellView[][] cells;
    
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
    
    public void setModel(IOthello model) {
        this.model = model;
        for(int i = 0 ; i < cells.length; ++i) {
        	for(int j = 0 ; j < cells[i].length; ++j) {
                cells[i][j].repaint();
            }
        }
    }
    
    private void createModel(IOthello model) {
        this.model = model;
    }
    
    private void createView() {
        cells = new CellView[model.getBoard().getSize()][model.getBoard().getSize()];
        for(int i = 0 ; i < cells.length; ++i) {
        	for(int j = 0 ; j < cells[i].length; ++j) {
                cells[i][j] = new CellView(this, new Coord(i,j));
            }
        }
    }
    
    private void placeComponents() {
        this.setLayout(new GridLayout(model.getBoard().getSize(), 
        		model.getBoard().getSize())); {
			for(int i = 0 ; i < cells.length; ++i) {
	        	for(int j = 0 ; j < cells[i].length; ++j) {
	            	this.add(cells[i][j]);
	            }
	        }
        }
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
//					: evt.getOldValue())).importantSets(), paint);
//			}
//        	
//        });
    }
    
    // TEST
    public static void main(String[] args) {
        class Bla {
            private static final String filename = "grille2.txt";
            JFrame mainFrame = new JFrame(filename);
            IOthello model;
            public Bla() {
                model = new Othello();
                mainFrame.add(new BoardView(model), BorderLayout.CENTER);
                mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
            
            public void display() {
                mainFrame.pack();
                mainFrame.setLocationRelativeTo(null);
                mainFrame.setVisible(true);
            }
        }
        
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                new Bla().display();
            }
            
        });
    }
    
}
