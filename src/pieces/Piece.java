package pieces;

import mecanics.Player;
import moves.Move;
import panels.Tile;
import resorsces.Colors;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public abstract class Piece
{
	protected Image image;
	protected Player player;
	protected boolean moved;

	public Piece(Player p)
	{
		moved = false;
		player = p;
		try
		{
			image = ImageIO.read(new File("pic//" + getClass().getSimpleName() + ".png"));
			image = changeColor(image, Color.WHITE, new Color(0, 0, 0, 0));
			image = changeColor(image, new Color(190, 190, 190), p.getNum() % 2 == 0 ? Colors.PIECE_WHITE : Colors.PIECE_BLACK);
		}
		catch(IOException e)
		{
			System.err.println("cant read image");
			image = null;
		}
	}

	public static Image changeColor(Image image, final Color from, final Color to)
	{
		ImageFilter filter = new RGBImageFilter()
		{
			@Override
			public int filterRGB(int x, int y, int rgb)
			{
				if(rgb == from.getRGB())
				{
					return to.getRGB();
				}
				return rgb;
			}
		};
		ImageProducer ip = new FilteredImageSource(image.getSource(), filter);
		return Toolkit.getDefaultToolkit().createImage(ip);
	}

	public abstract ArrayList<Move> getMoves(int from, Tile[][] board);

	protected boolean isinside(int x, int y, int boardSize)
	{
		//System.out.print(x+"   "+y);
		boolean b = x >= 0 && x < boardSize && y >= 0 && y < boardSize;
		//System.out.println("  "+b);
		return b;
	}

	protected boolean canMove(Tile[][] board, int to)
	{
		return board[to/8][to%8].isEmpty() || board[to/8][to%8].getPiece().getPlayer() != player || board[to/8][to%8].getPiece()!= this;
	}

	public Image getImage()
	{
		return image;
	}

	public Player getPlayer()
	{
		return player;
	}

	public boolean isMoved()
	{
		return moved;
	}

	public void move()
	{
		moved = true;
	}
}
