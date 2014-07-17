import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;

public class Tile extends JPanel {

	private boolean highlighted;
	private int num;
	private Piece piece;
	private Color c;

	public Tile(Dimension size, Point loc,final Board b, Piece p, final int num, Color c)
	{
		super();
		setSize(size);
		setLocation(loc);
		setLayout(null);
		piece = p;
		this.num = num;
		this.c = c;
		highlighted = false;
		addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				e.consume();
			}

			@Override
			public void mousePressed(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1)
				{
					b.move(num);
				}
				e.consume();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				e.consume();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				e.consume();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				e.consume();
			}
		});
	}

	public static Image changeColor(Image image , final Color from , final Color to)
	{
		ImageFilter filter = new RGBImageFilter()
		{
			@Override
			public int filterRGB(int x , int y , int rgb)
			{
				if(rgb == from.getRGB())
				{
					return to.getRGB();
				}
				return rgb;
			}
		};
		ImageProducer ip = new FilteredImageSource(image.getSource() , filter);
		return Toolkit.getDefaultToolkit().createImage(ip);
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		if(highlighted)
		{
			g.setColor(Color.BLUE);
		}
		else
		{
			g.setColor(c);
		}
		g.fillRect(0,0,getSize().width-2,getSize().height-2);
		if(piece != null)
		{
			piece.setImage(changeColor(piece.getImage(), Color.WHITE, c));
			g.drawImage(piece.getImage(),0,0,getSize().width-2,getSize().height-2,null);
		}
	}

	public Piece getPiece() {
		return piece;
	}

	public void setPiece(Piece piece) {
		this.piece = piece;
	}

	public int getNum() {
		return num;
	}

	public void setHighlighted(boolean highlighted) {
		this.highlighted = highlighted;
	}
}
