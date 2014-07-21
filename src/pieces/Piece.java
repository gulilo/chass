package pieces;

import mecanics.Player;

import java.awt.*;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;

public abstract class Piece
{
	protected Image image;
	protected Point[] moves;
	protected Player player;

	public Piece(Player p)
	{
		player = p;
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

	public Point[] getMoves() {
		return moves;
	}

	public Image getImage() {
		return image;
	}

	public Player getPlayer() {
		return player;
	}
}
