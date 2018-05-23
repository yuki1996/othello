package othello.view;

import java.awt.Color;
import java.awt.Graphics;

public enum DrawableCell {
	BLACK {
		@Override
		public void draw(Graphics g, CellView cv) {
			g.setColor(Color.BLACK);
			g.fillOval(cv.getWidth() / 10, cv.getHeight() / 10, 
					cv.getWidth() * 8 / 10, cv.getHeight() * 8 / 10);
		}
		
	},
	WHITE {
		@Override
		public void draw(Graphics g, CellView cv) {
			g.setColor(Color.BLACK);
			g.drawOval(cv.getWidth() / 10, cv.getHeight() / 10, 
					cv.getWidth() * 8 / 10, cv.getHeight() * 8 / 10);
		}
		
	},
	VALID_MOVE {
		@Override
		public void draw(Graphics g, CellView cv) {
			g.setColor(new Color(1,137,42));
			g.fillRect(0, 0, cv.getWidth(), cv.getHeight());
		}
		
	},
	INVALID_MOVE {
		@Override
		public void draw(Graphics g, CellView cv) {
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, cv.getWidth(), cv.getHeight());
		}
		
	};
	
	
	public abstract void draw (Graphics g, CellView cv);
}
