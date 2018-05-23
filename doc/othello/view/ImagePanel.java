package othello.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class ImagePanel extends JPanel{
	private BufferedImage image;
	public ImagePanel(String s) {
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		File f = new File("src/othello/view/img/"+s);
		try {
			image = ImageIO.read(f);
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, 50, 50, null);
	}
}
