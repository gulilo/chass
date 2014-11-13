package pieces;

import mecanics.Player;
import moves.PieceMove;

import java.util.ArrayList;

public class King extends Piece
{

	public King(Player p, int loc)
	{
		super(p, loc);
		code = 'K';
	}

	@Override
	public ArrayList<Integer> getTilesNumbers(Piece[][] board, int num)
	{
		ArrayList<Integer> a = new ArrayList<Integer>();

		int x = num / 8;
		int y = num % 8;

		for(int i = x - 1; i <= x + 1; i++)
		{
			for(int j = y - 1; j <= y + 1; j++)
			{
				if(isInside(i, j, 8))
				{
					if(canMove(board, i * 8 + j))
					{
						if(!isCheck(board, new PieceMove(loc, i * 8 + j)))
						{
							a.add(i * 8 + j);
						}
					}
				}
			}
		}
		return a;
	}
}
