package pieces;

import mecanics.Player;
import moves.Check;
import moves.Kill;
import moves.Move;
import moves.PieceMove;
import panels.Board;
import panels.Tile;

import java.util.ArrayList;

public class Bishop extends Piece
{
	public Bishop(Player p, int loc)
	{
		super(p, loc);
		code = 'B';
	}


	@Override
	public ArrayList<Move> getMoves(Tile[][] board)
	{
		ArrayList<Move> moves = new ArrayList<Move>();

		ArrayList<Integer> a = getTilesNumbers(board, loc);

		for(Integer num:a)
		{
			if(isCheck(board,num))
			{
				moves.add(new Check(loc,num));
			}
			else if(Board.getTile(num, board).isEmpty())
			{
				moves.add(new PieceMove(loc,num));
			}
			else
			{
				moves.add(new Kill(loc,num));
			}
		}
		return moves;
	}

	private boolean isCheck(Tile[][] board, int num)
	{
		ArrayList<Integer> a = getTilesNumbers(board, num);

		for(Integer i:a)
		{
			if(!Board.getTile(i,board).isEmpty() && Board.getTile(i,board).getPiece().getPlayer() != player && Board.getTile(i,board).getPiece() instanceof King)
			{
				return true;
			}
		}
		return false;
	}


	private ArrayList<Integer> getTilesNumbers(Tile[][] board, int num)
	{
		int[][] m = {{1,1,-1,-1},{1,-1,1,-1}};
		ArrayList<Integer> a = new ArrayList<Integer>();

		int x = num / 8;
		int y = num % 8;

		for (int i = 0; i < m[0].length; i++)
		{
			boolean flag = true;
			for (int j = 1; j < board.length && flag; j++)
			{
				if (isInside(x + j * m[0][i], y + j * m[1][i], 8))
				{
					if (canMove(board, (x + j * m[0][i]) * 8 + y + j * m[1][i]))
					{
						a.add((x + j * m[0][i]) * 8 + y + j * m[1][i]);
						if(!board[x + j * m[0][i]][y + j * m[1][i]].isEmpty() && board[x + j * m[0][i]][y + j * m[1][i]].getPiece().getPlayer() != player)
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
