package panels;

import mecanics.Player;
import pieces.*;

import javax.swing.*;
import java.awt.*;


public class Board extends JPanel
{
	private final int BOARD_SIZE = 8;

	private Tile tiles[][];
	private boolean wkcastling;

	public Board(Dimension size, Point loc, int tileSize, Player[] p)
	{
		super();
		setSize(size);
		setLocation(loc);
		setLayout(null);
		setBackground(Color.ORANGE);

		wkcastling = false;
		tiles = new Tile[BOARD_SIZE][BOARD_SIZE];
		int counter = 0;
		for(int i = 0; i < BOARD_SIZE; i++)
		{
			for(int j = 0; j < BOARD_SIZE; j++)
			{
				tiles[i][j] = new Tile(new Dimension(tileSize - 1, tileSize - 1), new Point(getSize().width / 2 - ((tileSize) * 4) + j * tileSize, getSize().height / 2 - ((tileSize) * 4) + i * tileSize), this, counter);
				add(tiles[i][j]);
				counter++;
			}
		}

		for(int i = 0; i < tiles.length; i++)
		{
			tiles[7][i].setPiece(p[0].getPieces()[i]);
		}
		for(int i = 0; i < tiles[1].length; i++)
		{
			tiles[6][i].setPiece(p[0].getPieces()[i + 8]);
		}

		for(int i = 0; i < tiles.length; i++)
		{
			tiles[0][i].setPiece(p[1].getPieces()[i]);
		}
		for(int i = 0; i < tiles[1].length; i++)
		{
			tiles[1][i].setPiece(p[1].getPieces()[i + 8]);
		}
	}

	private boolean isinside(int x, int y)
	{
		return x >= 0 && x < BOARD_SIZE && y >= 0 && y < BOARD_SIZE;
	}

	public boolean movePiece(int from, int to)
	{
		if(from == to)
		{
			return false;
		}
		if(getPiece(from) == null)
		{
			return false;
		}
		int[] arr = moves(from);
		int index = 0;
		boolean flag = true;
		while(arr[index] >= 0 && index < arr.length && flag)
		{
			if(arr[index] == to)
			{
				flag = false;
			} else
			{
				index++;
			}
		}
		if(!flag)
		{
			Tile f = getTile(from);
			Tile t = getTile(to);
			//TODO better castling
			if(f.getPiece() instanceof King && to == 62 && wkcastling)
			{
				f.getPiece().setMoved(true);
				getTile(63).getPiece().setMoved(true);
				t.setPiece(f.getPiece());
				getTile(61).setPiece(getTile(63).getPiece());
				f.setPiece(null);
				getTile(63).setPiece(null);
			} else if(t.getPiece() == null)
			{
				f.getPiece().setMoved(true);
				t.setPiece(f.getPiece());
				f.setPiece(null);
			} else
			{
				f.getPiece().setMoved(true);
				t.getPiece().getPlayer().kill(t.getPiece());
				t.setPiece(f.getPiece());
				f.setPiece(null);
			}
			return true;
		}
		return false;
	}

	public void highlight(int num)
	{
		int[] arr = moves(num);
		if(arr == null)
		{
			for(Tile[] t : tiles)
			{
				for(Tile tile : t)
				{
					tile.setHighlighted(false);
				}
			}
		} else
		{
			int index = 0;
			while(arr[index] >= 0 && index < arr.length)
			{
				getTile(arr[index++]).setHighlighted(true);
			}
		}
	}

	private int[] moves(int num)
	{
		if(num < 0 || getTile(num).isEmpty())
		{
			return null;
		} else
		{
			Piece p = getTile(num).getPiece();
			int x = num / 8;
			int y = num % 8;

			//TODO better way to store moves
			int[] ans = new int[64];
			for(int i = 0; i < ans.length; i++)
			{
				ans[i] = -1;
			}
			int index = 0;

			if(p instanceof Rook || p instanceof Bishop || p instanceof Queen)
			{
				boolean[] flags = new boolean[p.getMoves().length];
				for(int i = 0; i < flags.length; i++)
				{
					flags[i] = true;
				}
				for(int i = 1; i < BOARD_SIZE; i++)
				{
					for(int j = 0; j < flags.length; j++)
					{
						if(flags[j])
						{
							if(isinside((int) (x + i * p.getMoves()[j].getX()), (int) (y + i * p.getMoves()[j].getY())))
							{
								if(tiles[(int) (x + i * p.getMoves()[j].getX())][(int) (y + i * p.getMoves()[j].getY())].getPiece() == null)
								{
									ans[index++] = (int) (x + i * p.getMoves()[j].getX()) * 8 + (int) (y + i * p.getMoves()[j].getY());
								} else if(tiles[(int) (x + i * p.getMoves()[j].getX())][(int) (y + i * p.getMoves()[j].getY())].getPiece().getPlayer() != p.getPlayer())
								{
									ans[index++] = (int) (x + i * p.getMoves()[j].getX()) * 8 + (int) (y + i * p.getMoves()[j].getY());
									flags[j] = false;
								} else
								{
									flags[j] = false;
								}
							} else
							{
								flags[j] = false;
							}
						}
					}
				}
			} else if(p instanceof King || p instanceof Knight)
			{
				for(int j = 0; j < p.getMoves().length; j++)
				{
					if(isinside((int) (x + p.getMoves()[j].getX()), (int) (y + p.getMoves()[j].getY())))
					{
						if(tiles[(int) (x + p.getMoves()[j].getX())][(int) (y + p.getMoves()[j].getY())].getPiece() == null)
						{
							ans[index++] = (int) (x + p.getMoves()[j].getX()) * 8 + (int) (y + p.getMoves()[j].getY());
						} else if(tiles[(int) (x + p.getMoves()[j].getX())][(int) (y + p.getMoves()[j].getY())].getPiece().getPlayer() != p.getPlayer())
						{
							ans[index++] = (int) (x + p.getMoves()[j].getX()) * 8 + (int) (y + p.getMoves()[j].getY());
						}
					}
				}
				if(p instanceof King) //castling
				{
					if(!p.isMoved())
					{
						if(!getTile(63).isEmpty() && !getTile(63).getPiece().isMoved())
						{
							if(getTile(61).isEmpty() && getTile(62).isEmpty())
							{
								ans[index++] = 62;
								wkcastling = true;
							} else
							{
								wkcastling = false;
							}
						} else
						{
							wkcastling = false;
						}
						if(!getTile(56).isEmpty() && !getTile(56).getPiece().isMoved())
						{
							if(getTile(58).isEmpty() && getTile(57).isEmpty() && getTile(56).isEmpty())
							{
								ans[index++] = 57;
							}
						}
					} else
					{
						wkcastling = false;
					}
				}
			} else if(p instanceof Pawn)
			{
				Pawn p2 = (Pawn) p;
				if(isinside((int) (x + p.getMoves()[0].getX()), (int) (y + p.getMoves()[0].getY())))
				{
					if(tiles[(int) (x + p.getMoves()[0].getX())][(int) (y + p.getMoves()[0].getY())].getPiece() == null)
					{
						ans[index++] = (int) (x + p.getMoves()[0].getX()) * 8 + (int) (y + p.getMoves()[0].getY());
					}
					if(!p2.isMoved())
					{
						if(tiles[(int) (x + p.getMoves()[1].getX())][(int) (y + p.getMoves()[1].getY())].getPiece() == null)
						{
							ans[index++] = (int) (x + p.getMoves()[1].getX()) * 8 + (int) (y + p.getMoves()[1].getY());
						}
					}
					for(int i = 0; i < p2.getAttacks().length; i++)
					{
						if(isinside((int) (x + p2.getAttacks()[i].getX()), (int) (y + p2.getAttacks()[i].getY())))
						{
							if(tiles[(int) (x + p2.getAttacks()[i].getX())][(int) (y + p2.getAttacks()[i].getY())].getPiece() != null && tiles[(int) (x + p2.getAttacks()[i].getX())][(int) (y + p2.getAttacks()[i].getY())].getPiece().getPlayer() != p2.getPlayer())
							{
								ans[index++] = (int) (x + p2.getAttacks()[i].getX()) * 8 + (int) (y + p2.getAttacks()[i].getY());
							}
						}
					}
				}
			}
			return ans;
		}
	}

	private Tile getTile(int num)
	{
		return tiles[num / 8][num % 8];
	}

	public Piece getPiece(int num)
	{
		return getTile(num).getPiece();
	}
}