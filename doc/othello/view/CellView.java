package othello.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JComponent;

public class CellView extends JComponent {
	
	//ATTRIBUTS

	private static final Dimension DEFAULT_PREFERED_DIMENSION = new Dimension(30,30);
	private static final Color BORDER_COLOR = Color.BLACK;
	private static final int BORDER_SIZE = 1;
	
	private DrawableCell dc;
	
	//CONSTRUCTEURS
	
	public CellView(DrawableCell dc) {
		this.dc = dc;
		setPreferredSize(DEFAULT_PREFERED_DIMENSION);
		setBackground(Color.red);
		repaint();
	}
	
	//COMMANDES
	
	public void setDrawableCell(DrawableCell dc) {
		if (this.dc != dc) {
			this.dc = dc;
			repaint();
		}
	}
	
	public void paintComponent(Graphics g) {
		dc.draw(g, this);
		this.setBorder(BorderFactory.createLineBorder(BORDER_COLOR, BORDER_SIZE));
    }
}
