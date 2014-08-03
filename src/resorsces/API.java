package resorsces;

import mecanics.Core;
import panels.Board;

public class API
{

	private int turn;
	private Board board;
	private Core core;
	private int selected;

	public API(Board board, Core core)
	{
		turn = 0;
		this.board = board;
		this.core = core;
		selected = -1;
	}

	public void click(int num)
	{
		if(selected < 0)
		{
			if(board.getPiece(num) != null && board.getPiece(num).getPlayer() == core.getPlayers()[turn])
			{
				selected = num;
			}
		} else
		{
			if(board.movePiece(selected, num))
			{
				nextTurn();

			}
			selected = -1;
		}
		board.highlight(selected);
		core.update();
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
}
