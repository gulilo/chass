package moves;

import panels.Tile;

public class Check extends Move
{
	public Check(int num)
	{
		super(num);
	}

	@Override
	public void doit(Tile[][] board, int from, int to)
	{
		//TODO find better way
		if(board[to/8][to%8].isEmpty())
		{
			board[from/8][from%8].getPiece().move();
			board[to/8][to%8].setPiece(board[from/8][from%8].getPiece());
			board[from/8][from%8].setPiece(null);
		}
		else
		{
			board[to/8][to%8].getPiece().getPlayer().kill(board[to/8][to%8].getPiece());
			board[to/8][to%8].setPiece(board[from/8][from%8].getPiece());
			board[from/8][from%8].setPiece(null);
		}
		System.out.println("check");
	}
}
