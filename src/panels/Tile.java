package panels;

import mecanics.Core;
import pieces.Piece;
import resorsces.Colors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Tile extends JPanel
{
	private Color highlighted;
	private int num;
	private Piece piece;

	public Tile(Dimension size, Point loc, final int num, Piece p)
	{
		super();
		setSize(size);
		setLocation(loc);
		setLayout(null);

		this.num = num;
		highlighted = null;
		piece = p;
		addMouseListener(new MouseListener()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				e.consume();
			}

			public void mousePressed(MouseEvent e)
			{
				if(e.getButton() == MouseEvent.BUTTON1)
				{
					Core.api.click(num);
				}
				e.consume();
			}

			@Override
			public void mouseReleased(MouseEvent e)
			{
				e.consume();
			}

			@Override
			public void mouseEntered(MouseEvent e)
			{
				e.consume();
			}

			@Override
			public void mouseExited(MouseEvent e)
			{
				e.consume();
			}
		});
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(3));
		if(highlighted != null)
		{
			g2.setColor(highlighted);
		}
		else
		{
			int x = num / 8;
			int y = num % 8;
			if(x % 2 == y % 2)
			{
				g2.setColor(Colors.TILE_LIGHT);
			}
			else
			{
				g2.setColor(Colors.TILE_DARK);
			}
		}
		g2.fillRect(0, 0, getSize().width, getSize().height);
		if(piece != null)
		{
			if(piece.getImage() == null)
			{
				g2.setColor(Color.GREEN);
				g2.fillRect(0, 0, getSize().width, getSize().height);
			}
			else
			{
				g2.drawImage(piece.getImage(), 0, 0, getSize().width, getSize().height, null);
			}
		}
		if(num == Core.api.getSelected())
		{
			g2.setColor(Color.CYAN);
			g2.drawRect(0, 0, getSize().width - 1, getSize().height - 1);
		}
		g2.setColor(Color.yellow);
		g2.drawString("" + num, 20, 20);
	}

	public char[] getCode()
	{
		char[] c = {(char) ((num % 8) + 65), (char) (num / 8+49)};
		return c;
	}


	public boolean isEmpty()
	{
		return piece == null;
	}

	public Piece getPiece()
	{
		return piece;
	}

	public void setPiece(Piece piece)
	{
		this.piece = piece;
	}

	public void setHighlighted(Color highlighted)
	{
		this.highlighted = highlighted;
	}
}
