package moves;

import panels.Board;
import panels.Tile;

public abstract class Move
{
	protected int from,to;

	public Move(int from, int to)
	{
		this.from = from;
		this.to = to;
	}

	public abstract void doit(Tile[][] board, int from, int to);

	public int getTo()
	{
		return to;
	}

	public String getCode(Tile[][] board)
	{
		return ""+Board.getTile(to,board).getCode()[0]+Board.getTile(to,board).getCode()[1];
	}
}