package pieces;

import mecanics.Player;

import java.awt.*;

public class King extends Piece
{
	public King(Player p)
	{
		super(p);
		moves = new Point[8];
		moves[0] = new Point(1, 0);
		moves[1] = new Point(1, 1);
		moves[2] = new Point(1, -1);
		moves[3] = new Point(-1, 0);
		moves[4] = new Point(-1, 1);
		moves[5] = new Point(-1, -1);
		moves[6] = new Point(0, 1);
		moves[7] = new Point(0, -1);
	}

	@Override
	public boolean canMove(int from, int to)
	{
		return false;
	}
}
