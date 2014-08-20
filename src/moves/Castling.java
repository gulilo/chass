package moves;

import panels.Tile;

public class Castling extends Move
{
	public Castling(int from, int to)
	{
		super(from, to);
	}

	public void doit(Tile[][] board, int from, int to)
	{
		board[to/8][to%8].setPiece(board[from/8][from%8].getPiece());
		board[from/8][from%8].setPiece(null);
		if(from > to)
		{
			board[from/8][3].setPiece(board[7][0].getPiece());
			board[from/8][0].setPiece(null);
			board[from/8][3].getPiece().move(from+3);
		}
		else
		{
			board[from/8][5].setPiece(board[7][7].getPiece());
			board[from/8][7].setPiece(null);
			board[from/8][5].getPiece().move(from+5);
		}
		board[from/8][from%8].getPiece().move(to);
	}

	@Override
	public String getCode(Tile[][] board)
	{
		return from<to?"0-0-0":"0-0";
	}
}
