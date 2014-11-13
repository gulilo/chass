package pieces;

import mecanics.Player;
import moves.*;
import panels.Board;
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
import java.util.ConcurrentModificationException;
import java.util.ListIterator;

public abstract class Piece implements Cloneable
{
	protected Image image;
	protected Player player;
	protected boolean moved;
	protected int loc;
	protected char code;

	public Piece(Player p, int loc)
	{
		init(p, loc);
	}

	public Piece(Piece p)
	{
		init(p.getPlayer(), p.getLoc());
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

	private void init(Player p, int loc)
	{
		this.loc = loc;
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

	public ArrayList<Move> getMoves(Piece[][] board)
	{
		ArrayList<Move> moves = new ArrayList<Move>();
		ArrayList<Integer> a = getTilesNumbers(board, loc);
		if(player.isCheck())
		{
			for(Integer i : a)
			{
				if(!isCheck(board, new PieceMove(loc, i)))
				{
					moves.add(new CheckClear(loc, i));
				}
			}
		}
		else
		{
			for(Integer i : a)
			{
				if(board[i / 8][i % 8] != null)
				{
					moves.add(new Kill(loc, i));
				}
				else
				{
					moves.add(new PieceMove(loc, i));
				}
			}
			try
			{
				ListIterator<Move> iter = moves.listIterator();
				while(iter.hasNext())
				{
					Move m = iter.next();
					if(isCheck(board, m))
					{
						iter.remove();
						iter.add(new Check(m));
					}
				}


				/*ArrayList<Move> removes = new ArrayList<Move>();
				for(Move m : moves)
				{
					if(isCheck(board, m))
					{
						moves.add(new Check(m));
						removes.add(m);
					}
				}
				moves.removeAll(removes);*/
			}
			catch(ConcurrentModificationException e)
			{
				System.err.println("something");
			}
		}
		return moves;
	}

	protected boolean isCheck(Piece[][] board, Move m)
	{
		Piece[][] temp = Board.doMove(board, m);
		return Board.isCheck(temp);
	}

	public abstract ArrayList<Integer> getTilesNumbers(Piece[][] board, int num);

	protected boolean isInside(int x, int y, int boardSize)
	{
		return x >= 0 && x < boardSize && y >= 0 && y < boardSize;
	}

	protected boolean canMove(Piece[][] board, int to)
	{
		return board[to / 8][to % 8] == null || board[to / 8][to % 8].getPlayer() != player;
	}

	public Image getImage()
	{
		return image;
	}

	public Player getPlayer()
	{
		return player;
	}

	public int getLoc()
	{
		return loc;
	}

	public void move(int newLoc, Piece[][] board)
	{
		board[loc / 8][loc % 8] = null;
		if(board[newLoc / 8][newLoc % 8] != null)
		{
			board[newLoc / 8][newLoc % 8].getPlayer().kill(board[newLoc / 8][newLoc % 8]);
		}
		board[newLoc / 8][newLoc % 8] = this;
		loc = newLoc;
		moved = true;
	}

	public char getCode()
	{
		return code;
	}

	public Piece clone()
	{
		try
		{
			return (Piece) super.clone();
		}
		catch(CloneNotSupportedException e)
		{
			e.printStackTrace();
		}
		return null;
	}


}
