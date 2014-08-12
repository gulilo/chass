package panels;

import mecanics.Player;
import moves.Check;
import moves.Kill;
import moves.Move;
import moves.PieceMove;
import pieces.Piece;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class Board extends JPanel
{

	private Tile tiles[][];

	public Board(Dimension size, Point loc, int tileSize, Player[] players)
	{
		super();
		setSize(size);
		setLocation(loc);
		setLayout(null);
		setBackground(Color.ORANGE);

		int BOARD_SIZE = 8;
		tiles = new Tile[BOARD_SIZE][BOARD_SIZE];
		int counter = 0;
		for(int i = 0; i < BOARD_SIZE; i++)
		{
			for(int j = 0; j < BOARD_SIZE; j++)
			{
				tiles[i][j] = new Tile(new Dimension(tileSize - 1, tileSize - 1), new Point(getSize().width / 2 - ((tileSize) * 4) + j * tileSize, getSize().height / 2 - ((tileSize) * 4) + i * tileSize), this, counter);
				add(tiles[i][j]);
				counter++;
			}
		}
		players[0].start(tiles);
		players[1].start(tiles);

		//tiles[3][4].setPiece(new Rook(players[0]));
		//tiles[0][7].setPiece(new King(players[1]));
		//tiles[2][4].setPiece(new Pawn(players[1]));
	}


	public boolean movePiece(int from, int to)
	{
		if(from == to)
		{
			return false;
		}
		if(getPiece(from) == null)
		{
			return false;
		}

		ArrayList<Move> arr = moves(from);
		if(arr != null)
		{
			for(Move move : arr)
			{
				if(move.getNum() == to)
				{
					move.doit(tiles, from, to);
					return true;
				}
			}
		}

		return false;
	}

	public void highlight(int num)
	{
		ArrayList<Move> arr = moves(num);
		if(arr == null)
		{
			for(Tile[] t : tiles)
			{
				for(Tile tile : t)
				{
					tile.setHighlighted(null);
				}
			}
		}
		else
		{
			for(Move move:arr)
			{
				if(move instanceof PieceMove)
				{
					getTile(move.getNum()).setHighlighted(Color.BLUE);
				}
				else if(move instanceof Kill)
				{
					getTile(move.getNum()).setHighlighted(Color.RED);
				}
				else if(move instanceof Check)
				{
					getTile(move.getNum()).setHighlighted(Color.GREEN);
				}
			}
		}
	}

	private ArrayList<Move> moves(int num)
	{
		if(num < 0 || getTile(num).isEmpty())
		{
			return null;
		}
		else
		{
			Piece p = getTile(num).getPiece();
			return p.getMoves(num, tiles);
		}
	}

	private Tile getTile(int num)
	{
		return tiles[num / 8][num % 8];
	}

	public Piece getPiece(int num)
	{
		return getTile(num).getPiece();
	}
}