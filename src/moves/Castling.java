package moves;

import panels.Tile;

public class Castling extends Move
{
	public Castling(int num)
	{
		super(num);
	}

	public void doit(Tile[][] board, int from, int to)
	{
		board[to/8][to%8].setPiece(board[from/8][from%8].getPiece());
		board[from/8][from%8].setPiece(null);
		if(from > to)
		{
			board[7][3].setPiece(board[7][0].getPiece());
			board[7][0].setPiece(null);
		}
		else
		{
			board[7][5].setPiece(board[7][7].getPiece());
			board[7][7].setPiece(null);
		}
	}
}
