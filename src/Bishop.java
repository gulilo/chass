import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Bishop extends Piece
{
	public Bishop()
	{
		try {
			image = ImageIO.read(new File("pic//b.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public boolean move(int tx, int ty,int ox,int oy)
	{
		return Math.abs(tx - ox) == Math.abs(ty - oy);
	}
}
