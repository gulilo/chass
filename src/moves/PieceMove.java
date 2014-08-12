package moves;

import panels.Tile;

public class PieceMove extends Move
{
	public PieceMove(int num)
	{
		super(num);
	}

	public void doit(Tile[][] board, int from, int to)
	{
		board[from/8][from%8].getPiece().move();
		board[to/8][to%8].setPiece(board[from/8][from%8].getPiece());
		board[from/8][from%8].setPiece(null);
	}
}
