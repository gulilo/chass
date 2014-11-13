package pieces;

import mecanics.Player;
import panels.Board;

import java.util.ArrayList;

public class Pawn extends Piece
{

	public Pawn(Player p, int loc)
	{
		super(p, loc);
		code = ' ';
	}

	@Override
	public ArrayList<Integer> getTilesNumbers(Piece[][] board, int num)
	{
		Board.getPiece(num, board).getPlayer().getNum();
		int p = Board.getPiece(num, board).getPlayer().getNum() == 0 ? -1 : 1;
		ArrayList<Integer> a = new ArrayList<Integer>();

		int x = num / 8;
		int y = num % 8;
		if(isInside(x + (p * 2), y, 8))
		{
			if(!moved && canMove(board, (x + (p * 2)) * 8 + y) && board[x + (p * 2)][y] == null && board[x + p][y] == null)
			{
				a.add((x + (p * 2)) * 8 + y);
			}
		}
		if(isInside(x + p, y, 8))
		{
			if(canMove(board, (x + p) * 8 + y) && board[x + p][y] == null)
			{
				a.add((x + p) * 8 + y);
			}
		}

		if(isInside(x + p, y + 1, 8))
		{
			if(canMove(board, (x + p) * 8 + y + 1) && board[x + p][y + 1] != null && board[x + p][y + 1].getPlayer() != getPlayer())
			{
				a.add((x + p) * 8 + y + 1);
			}
		}
		if(isInside(x + p, y - 1, 8))
		{
			if(canMove(board, (x + p) * 8 + y - 1) && board[x + p][y - 1] != null && board[x + p][y - 1].getPlayer() != getPlayer())
			{
				a.add((x + p) * 8 + y - 1);
			}
		}
		return a;
	}
}
