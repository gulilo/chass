package pieces;

import mecanics.Player;
import resorsces.Colors;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Knight extends Piece
{
	public Knight(Player p,int n) {
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


		try {
			image = ImageIO.read(new File("pic//Knight.png"));
			image = changeColor(image, Color.WHITE, new Color(0, 0, 0, 0));
			image = changeColor(image, new Color(190, 190, 190), n % 2 == 0 ? Colors.PIECE_BLACK : Colors.PIECE_WHITE);
		} catch (IOException e) {
			System.err.println("cant read image");
			image = null;
		}
	}
}
