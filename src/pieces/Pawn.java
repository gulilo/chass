package pieces;

import mecanics.Player;

import java.awt.*;

public class Pawn extends Piece
{
	protected Point[] attacks;

	public Pawn(Player p)
	{
		super(p);
		moves = new Point[2];
		moves[0] = p.getNum() == 0 ? new Point(-1, 0) : new Point(1, 0);
		moves[1] = p.getNum() == 0 ? new Point(-2, 0) : new Point(2, 0);
		attacks = new Point[2];
		attacks[0] = p.getNum() == 0 ? new Point(-1, -1) : new Point(1, 1);
		attacks[1] = p.getNum() == 0 ? new Point(-1, 1) : new Point(1, -1);
	}

	@Override
	public boolean canMove(int from, int to)
	{
		if(player.getNum() == 0)
		{
			if(moved)
			{
				return from % 8 - to % 8 == 0 && from / 8 - to / 8 == 1;
			} else
			{
				return from % 8 - to % 8 == 0 && (from / 8 - to / 8 == 2 || from / 8 - to / 8 == 1);
			}
		} else
		{
			if(moved)
			{
				return from % 8 - to % 8 == 0 && from / 8 - to / 8 == -1;
			} else
			{
				return from % 8 - to % 8 == 0 && (from / 8 - to / 8 == -2 || from / 8 - to / 8 == -1);
			}
		}
	}

	public Point[] getAttacks()
	{
		return attacks;
	}
}
