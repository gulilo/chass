package pieces;

import mecanics.Player;
import moves.Check;
import moves.Kill;
import moves.Move;
import moves.PieceMove;
import panels.Tile;

import java.util.ArrayList;

public class Pawn extends Piece
{

	public Pawn(Player p, int loc)
	{
		super(p, loc);
		code = ' ';
	}

	@Override
	public ArrayList<Move> getMoves(Tile[][] board)
	{
		ArrayList<Move> moves = new ArrayList<Move>();

		int x = loc / 8;
		int y = loc % 8;

		//TODO find better way
		if(player.getNum() == 0)
		{
			if(isInside(x - 1, y, 8) && board[x - 1][y].isEmpty())
			{
				int num = (x - 1) * 8 + y;
				if(isCheck(board, num))
				{
					moves.add(new Check(loc,num));
				}
				else
				{
					moves.add(new PieceMove(loc,num));
				}
			}
			if(isInside(x - 1, y - 1, 8) && !board[x - 1][y - 1].isEmpty() && board[x - 1][y - 1].getPiece().getPlayer() != board[x][y].getPiece().getPlayer())
			{
				int num = (x - 1) * 8 + y - 1;
				if(isCheck(board, num))
				{
					moves.add(new Check(loc,num));
				}
				else
				{
					moves.add(new Kill(loc,num));
				}
			}
			if(isInside(x - 1, y + 1, 8) && !board[x - 1][y + 1].isEmpty() && board[x - 1][y + 1].getPiece().getPlayer() != board[x][y].getPiece().getPlayer())
			{
				int num = (x - 1) * 8 + y + 1;
				if(isCheck(board, num))
				{
					moves.add(new Check(loc,num));
				}
				else
				{
					moves.add(new Kill(loc,num));
				}
			}
			if(!board[x][y].getPiece().isMoved() && board[x - 1][y].isEmpty() && board[x - 2][y].isEmpty())
			{
				int num = (x - 2) * 8 + y;
				if(isCheck(board, num))
				{
					moves.add(new Check(loc,num));
				}
				else
				{
					moves.add(new PieceMove(loc,num));
				}
			}
		}
		else
		{
			if(isInside(x + 1, y, 8) && board[x + 1][y].isEmpty())
			{
				int num = (x + 1) * 8 + y;
				if(isCheck(board, num))
				{
					moves.add(new Check(loc,num));
				}
				else
				{
					moves.add(new PieceMove(loc,num));
				}
			}
			if(isInside(x + 1, y - 1, 8) && !board[x + 1][y - 1].isEmpty() && board[x + 1][y - 1].getPiece().getPlayer() != board[x][y].getPiece().getPlayer())
			{
				int num = (x + 1) * 8 + y - 1;
				if(isCheck(board, num))
				{
					moves.add(new Check(loc,num));
				}
				else
				{
					moves.add(new Kill(loc,num));
				}
			}
			if(isInside(x + 1, y + 1, 8) && !board[x + 1][y + 1].isEmpty() && board[x + 1][y + 1].getPiece().getPlayer() != board[x][y].getPiece().getPlayer())
			{
				int num = (x + 1) * 8 + y + 1;
				if(isCheck(board, num))
				{
					moves.add(new Check(loc,num));
				}
				else
				{
					moves.add(new Kill(loc,num));
				}
			}
			if(!board[x][y].getPiece().isMoved() && board[x + 1][y].isEmpty() && board[x + 2][y].isEmpty())
			{
				int num = (x + 2) * 8 + y;
				if(isCheck(board, num))
				{
					moves.add(new Check(loc,num));
				}
				else
				{
					moves.add(new PieceMove(loc,num));
				}
			}
		}
		return moves;
	}

	private boolean isCheck(Tile[][] board, int num)
	{
		int x = num / 8;
		int y = num % 8;
		if(player.getNum() == 0)
		{
			if(isInside(x - 1, y - 1, 8) && !board[x - 1][y - 1].isEmpty() && board[x - 1][y - 1].getPiece().getPlayer() != player && board[x - 1][y - 1].getPiece() instanceof King)
			{
				return true;
			}
			if(isInside(x - 1, y + 1, 8) && !board[x - 1][y + 1].isEmpty() && board[x - 1][y + 1].getPiece().getPlayer() != player && board[x - 1][y + 1].getPiece() instanceof King)
			{
				return true;
			}
		}
		else
		{
			if(isInside(x + 1, y - 1, 8) && !board[x + 1][y - 1].isEmpty() && board[x + 1][y - 1].getPiece().getPlayer() != player && board[x + 1][y - 1].getPiece() instanceof King)
			{
				return true;
			}
			if(isInside(x + 1, y + 1, 8) && !board[x + 1][y + 1].isEmpty() && board[x + 1][y + 1].getPiece().getPlayer() != player && board[x + 1][y + 1].getPiece() instanceof King)
			{
				return true;
			}
		}
		return false;
	}
}
