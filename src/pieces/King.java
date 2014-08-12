package pieces;

import mecanics.Player;
import moves.Castling;
import moves.Kill;
import moves.Move;
import moves.PieceMove;
import panels.Tile;

import java.util.ArrayList;

public class King extends Piece
{
	public King(Player p)
	{
		super(p);
	}

	@Override
	public ArrayList<Move> getMoves(int from, Tile[][] board)
	{
		ArrayList<Move> moves = new ArrayList<Move>();

		int x = from/8;
		int y = from%8;

		for(int i = -1;i<2;i++)
		{
			for(int j = -1;j<2;j++)
			{
				if(isinside(x + i,y + j,8))
				{
					if(board[x+i][y+j].isEmpty())
					{
						moves.add(new PieceMove((x+i)*8+(y+j)));
					}
					else if(board[x+i][y+j].getPiece().getPlayer() != board[x][y].getPiece().getPlayer())
					{
						moves.add(new Kill((x+i)*8+(y+j)));
					}
				}
			}
		}

		if(!board[x][y].getPiece().isMoved() && !board[x][y+3].isEmpty() && !board[x][y+3].getPiece().isMoved() && board[x][y+1].isEmpty() && board[x][y+2].isEmpty())
		{
			moves.add(new Castling(62));
		}
		if(!board[x][y].getPiece().isMoved() && !board[x][y-4].isEmpty() && !board[x][y-4].getPiece().isMoved() && board[x][y-1].isEmpty() && board[x][y-2].isEmpty() && board[x][y-3].isEmpty())
		{
			moves.add(new Castling(58));
		}
		return moves;
	}
}
