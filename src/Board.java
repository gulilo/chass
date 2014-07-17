import javax.swing.*;
import java.awt.*;

public class Board
{
	private final Color DARK = new Color(150,75,0);
	private final Color LIGHT = new Color(200,100,0);
	private final int BOARD_SIZE = 8;
	private Tile tiles[][];
	private Tile selected;

	public Board(int tileSize)
	{
		selected = null;
		tiles = new Tile[BOARD_SIZE][BOARD_SIZE];
		int counter = 0,num = 0;
		for(int i = 0;i<BOARD_SIZE;i++)
		{
			for(int j = 0;j<BOARD_SIZE;j++)
			{
				Point START_POINT = new Point(50, 50);
				tiles[i][j] = new Tile(new Dimension(tileSize,tileSize),new Point(START_POINT.x+(j*tileSize), START_POINT.y+(i*tileSize)),this, null,counter,num%2==0?LIGHT:DARK);
				counter++;
				num++;
			}
			num++;
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
				t.paintTile(g);
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
		Tile t = tiles[i][j];
		if(selected == null && t.getPiece() != null)
		{
			selected = t;
			highlight();
		}
		else if(selected != null)
		{
			if(t != selected)
			{
				if (selected.getPiece().move(i, j, selected.getNum() / 8, selected.getNum() % 8))
				{
					t.setPiece(selected.getPiece());
					selected.setPiece(null);
				}
			}
			selected = null;
			highlight();
		}
	}

	private void highlight()
	{
		if(selected == null)
		{
			for(int i = 0;i<tiles.length;i++)
			{
				for(int j = 0;j<tiles[i].length;j++)
				{
					tiles[i][j].setHighlighted(false);
				}
			}
		}
		else
		{
			for (int i = 0; i < tiles.length; i++) {
				for (int j = 0; j < tiles[i].length; j++) {
					if(selected.getPiece().move(i,j,selected.getNum()/8,selected.getNum()%8))
					{
						tiles[i][j].setHighlighted(true);
					}
				}
			}
		}
	}
}