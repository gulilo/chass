package pieces;

import mecanics.Player;

import java.util.ArrayList;

public class Knight extends Piece
{

	private final int[][] m = {{2, 2, -2, -2, 1, 1, -1, -1}, {1, -1, 1, -1, 2, -2, 2, -2}};

	public Knight(Player p, int loc)
	{
		super(p, loc);
		code = 'N';
	}

	@Override
	public ArrayList<Integer> getTilesNumbers(Piece[][] board, int num)
	{
		ArrayList<Integer> ans = new ArrayList<Integer>();
		int x = num / 8;
		int y = num % 8;

		for(int i = 0; i < m[0].length; i++)
		{
			if(isInside((x + m[0][i]), y + m[1][i], 8))
			{
				if(canMove(board, (x + m[0][i]) * 8 + y + m[1][i]))
				{
					ans.add((x + m[0][i]) * 8 + y + m[1][i]);
				}
			}
		}

		return ans;
	}
}
