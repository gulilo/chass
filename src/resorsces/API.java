package resorsces;

import mecanics.Core;
import mecanics.Player;
import moves.Castling;
import moves.Move;
import panels.Board;
import pieces.Piece;

public class API
{
	private int turn;
	private Board board;
	private Core core;
	private int selected;
	private String log;


	public API(Board board, Core core)
	{
		turn = 0;
		this.board = board;
		this.core = core;
		selected = -1;
		log = "";
	}

	public void click(int num)
	{
		if(selected < 0)
		{
			if(board.getPiece(num) != null && board.getPiece(num).getPlayer() == core.getPlayers()[turn])
			{
				selected = num;
			}
		}
		else
		{
			if(board.movePiece(selected, num))
			{
				nextTurn();
			}
			selected = -1;
			if(isGameOver())
			{
				core.gameOver();
			}
		}
		board.highlight(selected);
		core.update();
	}

	private boolean isGameOver()
	{
		if(core.getPlayers()[turn].isCheck())
		{
			for(Piece piece:core.getPlayers()[turn].getPieces())
			{
				if(!piece.getMoves(board.getBoard()).isEmpty())
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


	public int getSelected()
	{
		return selected;
	}


	public void log(Piece p, Move move)
	{
		String s = "";
		if(move instanceof Castling)
		{
			s+= "0-0";
		}
		else
		{
			s += "" + p.getCode() +move.getCode(board.getBoard()) + '\n';
		}
		log+=s;
	}

	public String getLog()
	{
		return log;
	}
}
