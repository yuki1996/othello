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

/**
 * Classe permettant de créer des espaces contenant des images avec un fond transparent 
 *
 */
public class ImagePanel extends JPanel{
	private BufferedImage image;
	
	/**
	 * Constructeur de la classe ImagePanel
	 * @param s : chemin de l'image
	 */
	public ImagePanel(String s) {
		File f = new File("src/othello/view/img/"+s);
		try {
			image = ImageIO.read(f);
		} catch (IOException e) {
			e.printStackTrace();
		} 
		setOpaque(false);
	}
	
	/**
	 * M�thode permettant de cr�er l'image
	 */
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this.getHeight(), this.getWidth(), null);
	}
}
