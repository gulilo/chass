package pieces;

import mecanics.Player;

import java.awt.*;

public class Bishop extends Piece
{
	public Bishop(Player p)
	{
		super(p);
		moves = new Point[4];
		moves[0] = new Point(1,1);
		moves[1] = new Point(-1,1);
		moves[2] = new Point(1,-1);
		moves[3] = new Point(-1,-1);
	}
}