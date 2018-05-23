package othello.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;

public enum DrawableCell {
	BLACK {
		@Override
		public void draw(Graphics g, CellView cv) {
			g.setColor(new Color(1,137,42));
			g.fillRect(0, 0, cv.getWidth(), cv.getHeight());
			g.setColor(Color.BLACK);
			g.fillOval(cv.getWidth() / 10, cv.getHeight() / 10, 
					cv.getWidth() * 8 / 10, cv.getHeight() * 8 / 10);
		}
		
	},
	WHITE {
		@Override
		public void draw(Graphics g, CellView cv) {
			g.setColor(new Color(1,137,42));
			g.fillRect(0, 0, cv.getWidth(), cv.getHeight());
			g.setColor(Color.WHITE);
			g.fillOval(cv.getWidth() / 10, cv.getHeight() / 10, 
					cv.getWidth() * 8 / 10, cv.getHeight() * 8 / 10);
			g.setColor(Color.black);
			g.drawOval(cv.getWidth() / 10, cv.getHeight() / 10, 
					cv.getWidth() * 8 / 10, cv.getHeight() * 8 / 10);
			
		}
		
	},
	VALID_MOVE {
		@Override
		public void draw(Graphics g, CellView cv) {
			g.setColor(new Color(1,137,42));
			g.fillRect(0, 0, cv.getWidth(), cv.getHeight());
			g.setColor(Color.RED);
			g.fillRect(cv.getWidth() / 2 - (cv.getWidth() * 1 / 10), cv.getHeight() / 2 - (cv.getHeight() * 1 / 10), 
					cv.getWidth() * 2 / 10, cv.getHeight() * 2 / 10);
		}
	},
	INVALID_MOVE {
		@Override
		public void draw(Graphics g, CellView cv) {
			g.setColor(new Color(1,137,42));
			g.fillRect(0, 0, cv.getWidth(), cv.getHeight());
		}
		
	};
	
	
	public abstract void draw (Graphics g, CellView cv);
}
