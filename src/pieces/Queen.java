package pieces;

import mecanics.Player;
import moves.Kill;
import moves.Move;
import moves.PieceMove;
import panels.Tile;

import java.util.ArrayList;

public class Queen extends Piece
{
	public Queen(Player p)
	{
		super(p);
	}

	@Override
	public ArrayList<Move> getMoves(int from, Tile[][] board)
	{
		ArrayList<Move> moves = new ArrayList<Move>();

		int x = from / 8;
		int y = from % 8;

		int[][] m = {{1, -1, 0, 0, 1, 1, -1, -1}, {0, 0, 1, -1, 1, -1, 1, -1}};
		boolean[] flags = {true, true, true, true, true, true, true, true};

		for(int i = 1; i < board.length; i++)
		{
			for(int j = 0; j < flags.length; j++)
			{
				if(flags[j])
				{
					if(isinside(x + i * m[0][j], y + i * m[1][j], 8))
					{
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

		return moves;
	}
}
