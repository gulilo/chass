package panels;

import mecanics.Core;
import mecanics.Player;
import moves.Check;
import moves.Kill;
import moves.Move;
import moves.PieceMove;
import pieces.*;

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
				tiles[i][j] = new Tile(new Dimension(tileSize - 1, tileSize - 1), new Point(getSize().width / 2 - ((tileSize) * 4) + j * tileSize, getSize().height / 2 - ((tileSize) * 4) + i * tileSize), counter,null);
				add(tiles[i][j]);
				counter++;
			}
		}
		start(players);
//		players[0].start(tiles);
//		players[1].start(tiles);

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
				if(move.getTo() == to)
				{
					Core.api.log(getTile(from,tiles).getPiece(),move);
					//System.out.println(Core.api.getLog());
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
					getTile(move.getTo(),tiles).setHighlighted(Color.BLUE);
				}
				else if(move instanceof Kill)
				{
					getTile(move.getTo(),tiles).setHighlighted(Color.RED);
				}
				else if(move instanceof Check)
				{
					getTile(move.getTo(),tiles).setHighlighted(Color.GREEN);
				}
			}
		}
	}

	private ArrayList<Move> moves(int num)
	{
		if(num < 0 || getTile(num,tiles).isEmpty())
		{
			return null;
		}
		else
		{
			Piece p = getTile(num,tiles).getPiece();
			return p.getMoves(tiles);
		}
	}

	private void start(Player[] players)
	{
		// TODO find better way to start the game
		ArrayList<Piece> pieces = new ArrayList<Piece>();
		Piece p = new Rook(players[0],56);
		getTile(56,tiles).setPiece(p);
		pieces.add(p);
		p = new Rook(players[0],63);
		getTile(63,tiles).setPiece(p);
		pieces.add(p);
		p = new Knight(players[0],57);
		getTile(57,tiles).setPiece(p);
		pieces.add(p);

		p = new Knight(players[0],62);
		getTile(62,tiles).setPiece(p);
		pieces.add(p);

		p = new Bishop(players[0],58);
		getTile(58,tiles).setPiece(p);
		pieces.add(p);
		p = new Bishop(players[0],61);
		getTile(61,tiles).setPiece(p);
		pieces.add(p);

		p = new King(players[0],59);
		getTile(59,tiles).setPiece(p);
		pieces.add(p);

		p = new Queen(players[0],60);
		getTile(60,tiles).setPiece(p);
		pieces.add(p);

		for(int i = 0;i<8;i++)
		{
			p = new Pawn(players[0],48+i);
			getTile(48+i,tiles).setPiece(p);
			pieces.add(p);
		}
		players[0].setPieces(pieces);


		pieces = new ArrayList<Piece>();
		p = new Rook(players[1],0);
		getTile(0,tiles).setPiece(p);
		pieces.add(p);
		p = new Rook(players[1],7);
		getTile(7,tiles).setPiece(p);
		pieces.add(p);
		p = new Knight(players[1],1);
		getTile(1,tiles).setPiece(p);
		pieces.add(p);

		p = new Knight(players[1],6);
		getTile(6,tiles).setPiece(p);
		pieces.add(p);

		p = new Bishop(players[1],2);
		getTile(2,tiles).setPiece(p);
		pieces.add(p);
		p = new Bishop(players[1],5);
		getTile(5,tiles).setPiece(p);
		pieces.add(p);

		p = new King(players[1],3);
		getTile(3,tiles).setPiece(p);
		pieces.add(p);

		p = new Queen(players[1],4);
		getTile(4,tiles).setPiece(p);
		pieces.add(p);

		for(int i = 0;i<8;i++)
		{
			p = new Pawn(players[1],8+i);
			getTile(8+i,tiles).setPiece(p);
			pieces.add(p);
		}
		players[1].setPieces(pieces);
	}

	public static Tile getTile(int num, Tile[][] tiles)
	{
		return tiles[num / 8][num % 8];
	}

	public Piece getPiece(int num)
	{
		return getTile(num,tiles).getPiece();
	}

	public Tile[][] getBoard()
	{
		return tiles;
	}
}