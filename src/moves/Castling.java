package moves;

import panels.Tile;
import pieces.Piece;

public class Castling extends Move
{
	public Castling(int from, int to)
	{
		super(from, to);
	}

	public void doit(Piece[][] board)
	{
		board[to / 8][to % 8] = board[from / 8][from % 8];
		board[from / 8][from % 8] = null;
		if(from > to)
		{
			board[from / 8][3] = board[7][0];
			board[from / 8][0] = null;
			board[from / 8][3].move(from + 3, board);
		}
		else
		{
			board[from / 8][5] = board[7][7];
			board[from / 8][7] = null;
			board[from / 8][5].move(from + 5, board);
		}
		board[from / 8][from % 8].move(to, board);
	}

	@Override
	public String getCode(Tile[][] board)
	{
		return from < to ? "0-0-0" : "0-0";
	}
}
