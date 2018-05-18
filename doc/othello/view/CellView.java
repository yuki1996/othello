package othello.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;

import othello.util.Coord;

public class CellView extends JButton {
	
	//ATTRIBUTS

	private static final Dimension DEFAULT_PREFERED_DIMENSION = new Dimension(30,30);
	private static final Color BORDER_COLOR = Color.BLACK;
	private static final int BORDER_SIZE = 1;
	
	
	private Coord coord;
	
	private BoardView bv;
	
	//CONSTRUCTEURS
	
	public CellView(BoardView bv, Coord coord) {
		this.bv = bv;
		this.coord = coord;
		setPreferredSize(DEFAULT_PREFERED_DIMENSION);
		repaint();
	}
	
	//COMMANDES
	
	public void paintComponent(Graphics g) {
		othello.util.Color c = bv.getModel().getBoard().getColor(coord);
		if (c == othello.util.Color.BLACK) {
        	DrawableCell.BLACK.draw(g, this);
		} else if (c == othello.util.Color.WHITE) {
        	DrawableCell.WHITE.draw(g, this);
		} else if (bv.getModel().getValidMoves(bv.getModel().getCurrentPlayer().getColor())
				.contains(coord)) {
			DrawableCell.VALID_MOVE.draw(g, this);
		} else {
			DrawableCell.INVALID_MOVE.draw(g, this);
		}
		this.setBorder(BorderFactory.createLineBorder(BORDER_COLOR, BORDER_SIZE));
    }
}
