package moves;

import panels.Tile;

public class Kill extends Move
{
	public Kill(int from, int to)
	{
		super(from, to);
	}

	public void doit(Tile[][] board, int from, int to)
	{
		board[to/8][to%8].getPiece().getPlayer().kill(board[to/8][to%8].getPiece());
		board[from/8][from%8].getPiece().move(to);
		board[to/8][to%8].setPiece(board[from/8][from%8].getPiece());
		board[from/8][from%8].setPiece(null);
	}
}
