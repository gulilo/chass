import pieces.Bishop;
import pieces.Rook;

import javax.swing.*;
import java.awt.*;

public class Board extends JPanel
{
    private Tile tiles[][];
	private Tile selected;

	public Board(Dimension size,Point loc, int tileSize)
	{
		super();
		setSize(size);
		setLocation(loc);
		setLayout(null);
        //setBackground(Color.RED);

		selected = null;
        int BOARD_SIZE = 8;
        tiles = new Tile[BOARD_SIZE][BOARD_SIZE];
        int counter = 0, num = 0;
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
                Color LIGHT = new Color(200, 100, 0);
                Color DARK = new Color(150, 75, 0);
                tiles[i][j] = new Tile(new Dimension(tileSize-1, tileSize-1), new Point(j * tileSize, i * tileSize), this, null, counter, num % 2 == 0 ? LIGHT : DARK);
				add(tiles[i][j]);
				counter++;
				num++;
			}
			num++;
		}
		tiles[0][0].setPiece(new Rook());
		tiles[0][7].setPiece(new Rook());
		tiles[0][2].setPiece(new Bishop());
		tiles[0][5].setPiece(new Bishop());
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