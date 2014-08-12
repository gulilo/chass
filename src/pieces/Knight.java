package pieces;

import mecanics.Player;
import moves.Check;
import moves.Kill;
import moves.Move;
import moves.PieceMove;
import panels.Tile;

import java.util.ArrayList;

public class Knight extends Piece
{

	private final int[][] m = {{2, 2, -2, -2, 1, 1, -1, -1}, {1, -1, 1, -1, 2, -2, 2, -2}};
	public Knight(Player p)
	{
		super(p);
	}

	@Override
	public ArrayList<Move> getMoves(int from, Tile[][] board)
	{
		ArrayList<Move> moves = new ArrayList<Move>();

		int x = from / 8;
		int y = from % 8;

		for(int i = 0; i < m[0].length; i++)
		{
			if(isinside(x + m[0][i], y + m[1][i], 8))
			{
				if(canMove(board,(x + m[0][i]) * 8 + (y + m[1][i])))
				{
					if(isCheck(board,(x + m[0][i]) * 8 + (y + m[1][i])))
					{
						moves.add(new Check((x + m[0][i]) * 8 + (y + m[1][i])));
					}
					else if(board[x + m[0][i]][y + m[1][i]].isEmpty())
					{
						moves.add(new PieceMove((x + m[0][i]) * 8 + (y + m[1][i])));
					}
					else if(board[x + m[0][i]][y + m[1][i]].getPiece().getPlayer() != board[x][y].getPiece().getPlayer())
					{
						moves.add(new Kill((x + m[0][i]) * 8 + (y + m[1][i])));
					}
				}
			}
		}
		return moves;
	}

	private boolean isCheck(Tile[][] board, int num)
	{
		for(int i = 0;i<m[0].length;i++)
		{
			if(isinside(num/8+m[0][i],num%8+m[1][i],8) && !board[num/8+m[0][i]][num%8+m[1][i]].isEmpty() && board[num/8+m[0][i]][num%8+m[1][i]].getPiece().getPlayer() != player && board[num/8+m[0][i]][num%8+m[1][i]].getPiece() instanceof King)
			{
				return true;
			}
		}
		return false;
	}
}
