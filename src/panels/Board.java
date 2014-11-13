package panels;

import mecanics.Player;
import moves.*;
import pieces.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class Board extends JPanel
{

	private Tile tiles[][];
	private Player[] players;

	public Board(Dimension size, Point loc, int tileSize, Player[] players)
	{
		super();
		setSize(size);
		setLocation(loc);
		setLayout(null);
		setBackground(Color.ORANGE);

		this.players = players;
		int BOARD_SIZE = 8;
		tiles = new Tile[BOARD_SIZE][BOARD_SIZE];
		int counter = 0;
		for(int i = 0; i < BOARD_SIZE; i++)
		{
			for(int j = 0; j < BOARD_SIZE; j++)
			{
				tiles[i][j] = new Tile(new Dimension(tileSize - 1, tileSize - 1), new Point(getSize().width / 2 - ((tileSize) * 4) + j * tileSize, getSize().height / 2 - ((tileSize) * 4) + i * tileSize), counter, null);
				add(tiles[i][j]);
				counter++;
			}
		}
		start();
//		players[0].start(tiles);
//		players[1].start(tiles);
	}

	public void highlight(Piece p)
	{
		ArrayList<Move> arr = moves(p);
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
			for(Move move : arr)
			{
				if(move instanceof PieceMove)
				{
					getTile(move.getTo(), tiles).setHighlighted(Color.BLUE);
				}
				else if(move instanceof Kill)
				{
					getTile(move.getTo(), tiles).setHighlighted(Color.RED);
				}
				else if(move instanceof Check || move instanceof CheckClear)
				{
					getTile(move.getTo(), tiles).setHighlighted(Color.GREEN);
				}
			}
		}
	}

	private ArrayList<Move> moves(Piece p)
	{
		if(p == null)
		{
			return null;
		}
		else
		{
			return p.getMoves(getPieces(tiles));
		}
	}

	private void start()
	{
		// TODO find better way to start the game
		ArrayList<Piece> pieces = new ArrayList<Piece>();
		Piece p = new Rook(players[0], 56);
		getTile(56, tiles).setPiece(p);
		pieces.add(p);
		p = new Rook(players[0], 63);
		getTile(63, tiles).setPiece(p);
		pieces.add(p);
		p = new Knight(players[0], 57);
		getTile(57, tiles).setPiece(p);
		pieces.add(p);

		p = new Knight(players[0], 62);
		getTile(62, tiles).setPiece(p);
		pieces.add(p);

		p = new Bishop(players[0], 58);
		getTile(58, tiles).setPiece(p);
		pieces.add(p);
		p = new Bishop(players[0], 61);
		getTile(61, tiles).setPiece(p);
		pieces.add(p);

		p = new King(players[0], 60);
		getTile(60, tiles).setPiece(p);
		pieces.add(p);

		p = new Queen(players[0], 59);
		getTile(59, tiles).setPiece(p);
		pieces.add(p);

		for(int i = 0; i < 8; i++)
		{
			p = new Pawn(players[0], 48 + i);
			getTile(48 + i, tiles).setPiece(p);
			pieces.add(p);
		}
		players[0].setPieces(pieces);


		pieces = new ArrayList<Piece>();
		p = new Rook(players[1], 0);
		getTile(0, tiles).setPiece(p);
		pieces.add(p);
		p = new Rook(players[1], 7);
		getTile(7, tiles).setPiece(p);
		pieces.add(p);
		p = new Knight(players[1], 1);
		getTile(1, tiles).setPiece(p);
		pieces.add(p);

		p = new Knight(players[1], 6);
		getTile(6, tiles).setPiece(p);
		pieces.add(p);

		p = new Bishop(players[1], 2);
		getTile(2, tiles).setPiece(p);
		pieces.add(p);
		p = new Bishop(players[1], 5);
		getTile(5, tiles).setPiece(p);
		pieces.add(p);

		p = new King(players[1], 4);
		getTile(4, tiles).setPiece(p);
		pieces.add(p);

		p = new Queen(players[1], 3);
		getTile(3, tiles).setPiece(p);
		pieces.add(p);

		for(int i = 0; i < 8; i++)
		{
			p = new Pawn(players[1], 8 + i);
			getTile(8 + i, tiles).setPiece(p);
			pieces.add(p);
		}
		players[1].setPieces(pieces);
	}

	public static Tile getTile(int num, Tile[][] tiles)
	{
		return tiles[num / 8][num % 8];
	}

	public static Piece getPiece(int num, Piece[][] board)
	{
		return board[num / 8][num % 8];
	}

	public Tile[][] getBoard()
	{
		return tiles;
	}

	public static Piece[][] doMove(Piece[][] board, Move move)
	{
		Piece[][] ans = copyBoard(board);
		move.doit(ans);
		return ans;
	}

	public void movePiece(Piece[][] board, Move move)
	{
		setTiles(doMove(board, move));
	}

	private static Piece[][] copyBoard(Piece[][] board)
	{
		Piece[][] ans = new Piece[board.length][board[0].length];
		for(int i = 0; i < board.length; i++)
		{
			for(int j = 0; j < board[i].length; j++)
			{
				if(board[i][j] != null)
				{
					ans[i][j] = board[i][j].clone();
				}
			}
		}
		return ans;
	}

	public Piece[][] getPieces(Tile[][] board)
	{
		Piece[][] ans = new Piece[board.length][board[0].length];
		for(int i = 0; i < board.length; i++)
		{
			for(int j = 0; j < board[i].length; j++)
			{
				if(board[i][j] != null)
				{
					ans[i][j] = board[i][j].getPiece();
				}
			}
		}
		return ans;
	}

	public static boolean isCheck(Piece[][] board)
	{
		for(Piece[] pl : board)
		{
			for(Piece p : pl)
			{
				if(p != null && !(p instanceof King))
				{
					ArrayList<Integer> moves = p.getTilesNumbers(board, p.getLoc());
					for(Integer i : moves)
					{
						if(Board.getPiece(i, board) != null && Board.getPiece(i, board).getPlayer() != p.getPlayer() && Board.getPiece(i, board) instanceof King)
						{
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	private void setTiles(Piece[][] board)
	{
		for(int i = 0; i < board.length; i++)
		{
			for(int j = 0; j < board[i].length; j++)
			{
				tiles[i][j].setPiece(board[i][j]);
			}
		}
	}
}