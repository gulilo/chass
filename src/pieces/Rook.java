package pieces;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Rook extends Piece
{
	public Rook()
	{
		try {
		image =	ImageIO.read(new File("pic//a.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean move(int tx, int ty, int ox, int oy) {
		return tx == ox || ty == oy;
	}
}
