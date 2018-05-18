package othello.view;

import java.awt.Color;
import java.awt.Graphics;

public enum DrawableCell {
	BLACK {

		@Override
		public void draw(Graphics g, CellView cv) {
			g.setColor(Color.BLACK);
			g.fillOval(0, 0, cv.getWidth(), cv.getHeight());
		}
		
	},
	WHITE {

		@Override
		public void draw(Graphics g, CellView cv) {
			g.setColor(Color.BLACK);
			g.drawOval(0, 0, cv.getWidth(), cv.getHeight());
		}
		
	},
	VALID_MOVE {

		@Override
		public void draw(Graphics g, CellView cv) {
			g.setColor(Color.GREEN);
			g.fillRect(0, 0, cv.getWidth(), cv.getHeight());
		}
		
	},
	INVALID_MOVE {

		@Override
		public void draw(Graphics g, CellView cv) {
			g.setColor(Color.RED);
			g.fillRect(0, 0, cv.getWidth(), cv.getHeight());
		}
		
	};
	
	public abstract void draw (Graphics g, CellView cv);
}
