import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;

public class Tile extends JPanel {

	private int num;
	private Piece piece;

	public Tile(Dimension size, Point loc,final Board b, Piece p, final int num)
	{
		super();
		setSize(size);
		setLocation(loc);
		setBackground(new Color(0, 0, 0, 0));
		piece = p;
		this.num = num;
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
		if(num %2 ==0)
		{
			g.setColor(Color.LIGHT_GRAY);
		}
		else
		{
			g.setColor(Color.DARK_GRAY);
		}
		g.fillRect(getLocation().x,getLocation().y,getSize().width-2,getSize().height-2);
		if(piece != null)
		{
			piece.setImage(changeColor(piece.getImage(), Color.WHITE, num % 2 == 0 ? Color.LIGHT_GRAY : Color.DARK_GRAY));
			g.drawImage(piece.getImage(),getLocation().x,getLocation().y,getSize().width-2,getSize().height-2,null);
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
}
