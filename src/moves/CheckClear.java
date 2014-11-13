package moves;

import mecanics.Core;
import pieces.Piece;

public class CheckClear extends Move
{
	public CheckClear(int loc, int to) {super(loc, to);}

	@Override
	public void doit(Piece[][] board)
	{
		Core.api.getCorrentPlayer().setCheck(false);
		board[from / 8][from % 8].move(to, board);
	}
}
