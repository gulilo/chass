package moves;

import panels.Tile;

public abstract class Move
{
	protected int num;

	public Move(int num)
	{
		this.num = num;
	}

	public abstract void doit(Tile[][] board, int from, int to);

	public int getNum()
	{
		return num;
	}

	@Override
	public String toString()
	{
		return getClass().getSimpleName()+"{" +
				"num=" + num +
				'}';
	}
}