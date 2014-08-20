package moves;

import panels.Board;
import panels.Tile;

public class Check extends Move
{

	public Check(int from, int to)
	{
		super(from, to);
	}

	@Override
	public void doit(Tile[][] board, int from, int to)
	{
		//TODO find better way
		Board.getTile(from,board).getPiece().getPlayer().setCheck(true);
		if(board[to/8][to%8].isEmpty())
		{
			board[from/8][from%8].getPiece().move(to);
			board[to/8][to%8].setPiece(board[from/8][from%8].getPiece());
			board[from/8][from%8].setPiece(null);
		}
		else
		{
			board[to/8][to%8].getPiece().getPlayer().kill(board[to/8][to%8].getPiece());
			board[from/8][from%8].getPiece().move(to);
			board[to/8][to%8].setPiece(board[from/8][from%8].getPiece());
			board[from/8][from%8].setPiece(null);
		}
	}

	@Override
	public String getCode(Tile[][] board)
	{
		return super.getCode(board)+'+';
	}
}
