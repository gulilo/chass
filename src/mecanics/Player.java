package mecanics;

import pieces.*;

public class Player
{
	private Piece[] pieces;
	private Piece[] deads;
	private int num;

	public Player(int num)
	{
		this.num = num;
		pieces = new Piece[16];
		pieces[4] = new King(this);
		pieces[3] = new Queen(this);
		pieces[7] = new Rook(this);
		pieces[0] = new Rook(this);
		pieces[6] = new Knight(this);
		pieces[1] = new Knight(this);
		pieces[2] = new Bishop(this);
		pieces[5] = new Bishop(this);
		for(int i = 8;i<pieces.length;i++)
		{
			pieces[i] = new Pawn(this);
		}
		deads = new Piece[16];
	}

	public Piece[] getPieces() {
		return pieces;
	}

	public Piece[] getDeads() {
		return deads;
	}

	public void setDeads(Piece[] deads) {
		this.deads = deads;
	}

	public int getNum() {
		return num;
	}
}
