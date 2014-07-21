package mecanics;

import pieces.*;

public class Player
{
	private Piece[] p;
	private Piece[] deads;
	private int num;

	public Player(int num)
	{
		this.num = num;
		p = new Piece[16];
		p[4] = new King(this,num);
		p[3] = new Queen(this,num);
		p[7] = new Rook(this,num);
		p[0] = new Rook(this,num);
		p[6] = new Knight(this,num);
		p[1] = new Knight(this,num);
		p[2] = new Bishop(this,num);
		p[5] = new Bishop(this,num);
		for(int i = 8;i<p.length;i++)
		{
			p[i] = new Pawn(this,num);
		}
		deads = new Piece[16];
	}

	public Piece[] getP() {
		return p;
	}

	public void setP(Piece[] p) {
		this.p = p;
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

	public void setNum(int num) {
		this.num = num;
	}
}
