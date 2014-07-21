package pieces;

import mecanics.Player;
import resorsces.Colors;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Pawn extends Piece
{
	protected Point[] attacks;

	public Pawn(Player p,int n)
	{
		super(p);

		moves = new Point[1];
		moves[0] = p.getNum()==0?new Point(1,0):new Point(-1,0);
		attacks = new Point[2];
		attacks[0] = p.getNum()==0?new Point(1,1):new Point(-1,-1);
		attacks[1] = p.getNum()==0?new Point(1,-1):new Point(-1,1);

		try {
			image = ImageIO.read(new File("pic//Pawn.png"));
			image = changeColor(image, Color.WHITE, new Color(0, 0, 0, 0));
			image = changeColor(image, new Color(190,190,190), n % 2 == 0 ? Colors.PIECE_BLACK : Colors.PIECE_WHITE);
		} catch (IOException e) {
			System.err.println("cant read image");
			image = null;
		}
	}

	public Point[] getAttacks() {
		return attacks;
	}
}
