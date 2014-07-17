import java.awt.*;

public abstract class Piece
{
	protected Image image;
	protected Point[] moves;
	protected Point[] attacks;

	public abstract boolean move(int tx, int ty,int ox,int oy);

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
}
