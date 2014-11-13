package moves;

import mecanics.Core;
import panels.Tile;
import pieces.Piece;

public class Check extends Move
{
	public Check(Move m)
	{
		super(m);
	}

	@Override
	public void doit(Piece[][] board)
	{
		Core.api.getOtherPlayer().setCheck(true);
		board[from / 8][from % 8].move(to, board);
	}

	@Override
	public String getCode(Tile[][] board)
	{
		return super.getCode(board) + '+';
	}
}
