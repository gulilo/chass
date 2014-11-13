package resorsces;

import mecanics.Core;
import mecanics.Player;
import moves.Castling;
import moves.Move;
import panels.Board;
import pieces.Piece;

import java.util.ArrayList;

public class API
{
	private int turn;
	private Board board;
	private Core core;
	private Piece selected;
	private String log;


	public API(Board board, Core core)
	{
		turn = 0;
		this.board = board;
		this.core = core;
		selected = null;
		log = "";
	}

	public void click(int num)
	{
		Piece p = Board.getPiece(num, board.getPieces(board.getBoard()));
		if(p != null && p.getPlayer().getNum() == turn && selected == null)
		{
			selected = p;
		}
		else if(selected != null && selected.getPlayer().getNum() == turn && selected != p)
		{
			ArrayList<Move> moves = selected.getMoves(board.getPieces(board.getBoard()));
			for(Move m : moves)
			{
				if(m.getTo() == num)
				{
					board.movePiece(board.getPieces(board.getBoard()), m);
					selected = null;
					nextTurn();
					if(isGameOver())
					{
						core.gameOver();
					}
				}
			}
		}
		else
		{
			selected = null;
		}
		board.highlight(selected);
		core.update();

	}

	private boolean isGameOver()
	{
		if(core.getPlayers()[turn].isCheck())
		{
			for(Piece piece : core.getPlayers()[turn].getPieces())
			{
				if(!piece.getTilesNumbers(board.getPieces(board.getBoard()),piece.getLoc()).isEmpty())
				{
					return false;
				}
			}
			return true;
		}
		return false;
	}

	public void nextTurn()
	{
		turn = (turn + 1) % 2;
		String s = turn == 0 ? "White" : "Black";
		System.out.println("turn " + s);
	}


	public Piece getSelected()
	{
		return selected;
	}


	public void log(Piece p, Move move)
	{
		String s = "";
		if(move instanceof Castling)
		{
			s += "0-0";
		}
		else
		{
			s += "" + p.getCode() + move.getCode(board.getBoard()) + '\n';
		}
		log += s;
	}

	public String getLog()
	{
		return log;
	}

	public Player getCorrentPlayer()
	{
		return core.getPlayers()[turn];
	}

	public Player getOtherPlayer()
	{
		return core.getPlayers()[(turn + 1) % 2];
	}
}
