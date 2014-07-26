package pieces;

import mecanics.Player;

import java.awt.*;

public class Rook extends Piece
{
	public Rook(Player p)
	{
		super(p);
		moves = new Point[4];
		moves[0] = new Point(1,0);
		moves[1] = new Point(0,1);
		moves[2] = new Point(-1,0);
		moves[3] = new Point(0,-1);
	}
}
