package pieces;

import mecanics.Player;
import resorsces.Colors;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;
import java.io.File;
import java.io.IOException;

public abstract class Piece
{
	protected Image image;
	protected Point[] moves;
	protected Player player;
	protected boolean moved;

	public Piece(Player p)
	{
		moved = false;
		player = p;
		try {
			image = ImageIO.read(new File("pic//" + getClass().getSimpleName() + ".png"));
			image = changeColor(image, Color.WHITE, new Color(0, 0, 0, 0));
			image = changeColor(image, new Color(190,190,190), p.getNum() % 2 == 0 ? Colors.PIECE_WHITE : Colors.PIECE_BLACK);
		} catch (IOException e) {
			System.err.println("cant read image");
			image = null;
		}
	}

	public static Image changeColor(Image image , final Color from , final Color to)
	{
		ImageFilter filter = new RGBImageFilter()
		{
			@Override
			public int filterRGB(int x , int y , int rgb)
			{
				if(rgb == from.getRGB())
				{
					return to.getRGB();
				}
				return rgb;
			}
		};
		ImageProducer ip = new FilteredImageSource(image.getSource() , filter);
		return Toolkit.getDefaultToolkit().createImage(ip);
	}

	public abstract boolean canMove(int from, int to);

	public Point[] getMoves() {
		return moves;
	}

	public Image getImage() {
		return image;
	}

	public Player getPlayer() {
		return player;
	}

	public boolean isMoved() {
		return moved;
	}

	public void setMoved(boolean moved) {
		this.moved = moved;
	}
}
