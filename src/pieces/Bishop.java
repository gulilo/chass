package pieces;

import mecanics.Player;
import moves.Check;
import moves.Kill;
import moves.Move;
import moves.PieceMove;
import panels.Tile;

import java.util.ArrayList;

public class Bishop extends Piece
{
	private final int[][] m = {{1,1,-1,-1},{1,-1,1,-1}};

	public Bishop(Player p)
	{
		super(p);
	}

	@Override
	public ArrayList<Move> getMoves(int from, Tile[][] board)
	{
		ArrayList<Move> moves = new ArrayList<Move>();

		int x = from / 8;
		int y = from % 8;

		boolean[] flags = {true,true,true,true};

		for(int i = 1; i < board.length; i++)
		{
			for(int j = 0;j<flags.length;j++)
			{
				if(flags[j])
				{
					if(isinside(x + i * m[0][j], y + i*m[1][j], 8))
					{
						if(canMove(board,(x + i*m[0][j]) * 8 + (y + i*m[1][j])))
						{
							if(isCheck(board,(x + i*m[0][j]) * 8 + (y + i*m[1][j])))
							{
								moves.add(new Check((x + i*m[0][j]) * 8 + (y + i*m[1][j])));
							}
							if(board[x + i * m[0][j]][y + i * m[1][j]].isEmpty())
							{
								moves.add(new PieceMove((x + i * m[0][j]) * 8 + (y + i * m[1][j])));
							}
							else if(board[x + i * m[0][j]][y + i * m[1][j]].getPiece().getPlayer() != board[x][y].getPiece().getPlayer())
							{
								moves.add(new Kill((x + i * m[0][j]) * 8 + (y + i * m[1][j])));
								flags[j] = false;
							}
							else
							{
								flags[j] = false;
							}
						}
					}
				}
			}
		}
		return moves;
	}

	private boolean isCheck(Tile[][] board, int num)
	{
		int x = num / 8;
		int y = num % 8;

		for(int i = 0; i < m[0].length; i++)
		{
			boolean flag = true;
			for(int j = 1; j < board.length && flag; j++)
			{
				if(isinside(x + j * m[0][i], y + i * m[1][i], 8) && !board[x + j * m[0][i]][y + i * m[1][i]].isEmpty() && board[x + j * m[0][i]][y + i * m[1][i]].getPiece() instanceof King)
				{
					return true;
				}
				else
				{
					flag = false;
				}
			}
		}
		return false;
	}
}
