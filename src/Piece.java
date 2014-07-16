import java.awt.*;

public abstract class Piece
{
	protected Image image;
	protected Point[] moves;
	protected Point[] attacks;
	protected int x,y;

	public abstract boolean move(int tx, int ty,int ox,int oy);

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}
