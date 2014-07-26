package pieces;

import mecanics.Player;

import java.awt.*;

public class Knight extends Piece
{
	public Knight(Player p)
	{
		super(p);
		moves = new Point[8];
		moves[0] = new Point(2,1);
		moves[1] = new Point(2,-1);
		moves[2] = new Point(-2,1);
		moves[3] = new Point(-2,-1);
		moves[4] = new Point(1,2);
		moves[5] = new Point(-1,2);
		moves[6] = new Point(1,-2);
		moves[7] = new Point(-1,-2);
	}
}
