package pieces;

import mecanics.Player;

import java.awt.*;

public class Pawn extends Piece
{
	protected Point[] attacks;
	private boolean moved;

	public Pawn(Player p)
	{
		super(p);
		moves = new Point[2];
		moves[0] = p.getNum()==0?new Point(-1,0):new Point(1,0);
		moves[1] = p.getNum()==0?new Point(-2,0):new Point(2,0);
		attacks = new Point[2];
		attacks[0] = p.getNum()==0?new Point(-1,-1):new Point(1,1);
		attacks[1] = p.getNum()==0?new Point(-1,1):new Point(1,-1);
		moved = false;
	}

	public Point[] getAttacks() {
		return attacks;
	}

	public boolean isMoved() {
		return moved;
	}

	public void setMoved(boolean moved) {
		this.moved = moved;
	}
}
