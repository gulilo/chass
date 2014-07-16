import javax.swing.*;
import java.awt.*;

public class Board
{
	private final int BOARD_SIZE = 8;
	private Tile tiles[][];
	private Tile selected;

	public Board(int tileSize)
	{
		selected = null;
		tiles = new Tile[BOARD_SIZE][BOARD_SIZE];
		int counter = 0;
		for(int i = 0;i<BOARD_SIZE;i++)
		{
			for(int j = 0;j<BOARD_SIZE;j++)
			{
				Point START_POINT = new Point(50, 50);
				tiles[j][i] = new Tile(new Dimension(tileSize,tileSize),new Point(START_POINT.x+(i*tileSize), START_POINT.y+(j*tileSize)),this, null,counter);
				counter++;
			}
		}
		tiles[3][3].setPiece(new Bishop());
	}

	public void addBoard(JPanel p)
	{
		for(int i = 0;i < BOARD_SIZE;i++)
			{
			for(int j = 0;j < BOARD_SIZE;j++)
			{
				p.add(tiles[i][j]);
			}
		}
	}

	public void drawBoard(Graphics g)
	{
		Graphics2D g2 = (Graphics2D)g;
		g2.setStroke(new BasicStroke(3));
		for(int i = 0;i<tiles.length;i++)
		{
			for(int j = 0;j<tiles[i].length;j++)
			{
				Tile t = tiles[i][j];
				t.paintComponent(g);
			}
		}
		if(selected!= null)
		{
			g2.setColor(Color.BLUE);
			g2.drawRect(selected.getLocation().x-1,selected.getLocation().y-1,selected.getSize().width-1,selected.getSize().height-1);
		}
	}

	public void move(int num)
	{
		int i = num/8;
		int j = num%8;
		System.out.println(num+" "+i+" "+j);
		Tile t = tiles[i][j];
		if(selected == null)
		{
			selected = t;
		}
		else
		{
			if(selected.getPiece() != null)
			{
				if (selected.getPiece().move(i, j, selected.getNum() / 8, selected.getNum() % 8))
				{
					t.setPiece(selected.getPiece());
					selected.setPiece(null);
					selected = null;
				}
				else
				{
					selected = t;
				}
			}
		}
	}
}