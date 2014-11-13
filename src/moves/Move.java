package moves;

import panels.Board;
import panels.Tile;
import pieces.Piece;

public abstract class Move
{
	protected int from, to;

	public Move(int from, int to)
	{
		this.from = from;
		this.to = to;
	}

	public Move(Move m)
	{
		from = m.from;
		to = m.to;
	}

	public abstract void doit(Piece[][] board);

	public int getTo()
	{
		return to;
	}

	public String getCode(Tile[][] board)
	{
		return "" + Board.getTile(to, board).getCode()[0] + Board.getTile(to, board).getCode()[1];
	}

	@Override
	public String toString()
	{
		return "Move [from=" + from + ", to=" + to + "]";
	}
}