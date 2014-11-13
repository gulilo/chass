package pieces;

import mecanics.Player;

import java.util.ArrayList;

public class Rook extends Piece
{
	public Rook(Player p, int loc)
	{
		super(p, loc);
		code = 'R';
	}

	public ArrayList<Integer> getTilesNumbers(Piece[][] board, int num)
	{
		int[][] m = {{1, -1, 0, 0}, {0, 0, 1, -1}};
		ArrayList<Integer> a = new ArrayList<Integer>();

		int x = num / 8;
		int y = num % 8;

		for(int i = 0; i < m[0].length; i++)
		{
			boolean flag = true;
			for(int j = 1; j < board.length && flag; j++)
			{
				if(isInside(x + j * m[0][i], y + j * m[1][i], 8))
				{
					if(canMove(board, (x + j * m[0][i]) * 8 + y + j * m[1][i]))
					{
						a.add((x + j * m[0][i]) * 8 + y + j * m[1][i]);
						if(board[x + j * m[0][i]][y + j * m[1][i]] != null)
						{
							flag = false;
						}
					}
					else
					{
						flag = false;
					}
				}
				else
				{
					flag = false;
				}
			}
		}
		return a;
	}
}
