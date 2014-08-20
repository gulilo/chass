package mecanics;

import panels.Tile;
import pieces.*;

import java.util.ArrayList;

public class Player
{
	private ArrayList<Piece> pieces;
	private ArrayList<Piece> deads;
	private int num;
	private boolean check;

	public Player(int num)
	{
		this.num = num;
		pieces = new ArrayList<Piece>();
		deads = new ArrayList<Piece>();
	}

	public void kill(Piece p)
	{
		pieces.remove(p);
		deads.add(p);
	}

	/*public void start(Tile[][] board)
	{
		Piece[][] p = {{new Rook(this), new Knight(this), new Bishop(this), new King(this), new Queen(this), new Bishop(this), new Knight(this), new Rook(this)}, {new Pawn(this), new Pawn(this), new Pawn(this), new Pawn(this), new Pawn(this), new Pawn(this), new Pawn(this), new Pawn(this)}};
		if(num==0)
		{
			for(int i = 0; i < p.length; i++)
			{
				for(int j = 0; j < p[i].length; j++)
				{
					pieces.add(p[i][j]);
					board[i + 6][j].setPiece(p[i==0?1:0][j]);
				}
			}
		}
		else
		{
			for(int i = 0; i < p.length; i++)
			{
				for(int j = 0; j < p[i].length; j++)
				{
					pieces.add(p[i][j]);
					board[num == 0 ? i + 6 : i][j].setPiece(p[i][j]);
				}
			}
		}
	}*/

	public ArrayList<Piece> getPieces()
	{
		return pieces;
	}

	public ArrayList<Piece> getDeads()
	{
		return deads;
	}

	public int getNum()
	{
		return num;
	}

	public void setPieces(ArrayList<Piece> pieces)
	{
		this.pieces = pieces;
	}

	public boolean isCheck()
	{
		return check;
	}

	public void setCheck(boolean check)
	{
		this.check = check;
	}
}
