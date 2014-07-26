package resorsces;

import mecanics.Core;
import panels.Board;

public class API {

	private int turn;
	private Board board;
	private Core core;

	public API(Board board, Core core)
	{
		turn = 0;
		this.board = board;
		this.core = core;
	}

	public void click(int num)
	{
		int x = num/8;
		int y = num%8;

		if(board.getSelected() == null)
		{
			if(board.getTiles()[x][y].getPiece() != null && board.getTiles()[x][y].getPiece().getPlayer() == core.getPlayers()[turn])
			{
				if(board.getTiles()[x][y].getPiece() != null)
				{
					board.setSelected(board.getTiles()[x][y]);
					board.setSx(x);
					board.setSy(y);
					board.highlight();
				}
			}
		}
		else
		{
			if(board.move(num))
			{
				nextTurn();
			}
			board.setSelected(null);
			board.setSy(-1);
			board.setSx(-1);
			board.highlight();
		}
		core.update();
	}

	public Board getBoard() {
		return board;
	}

	public void nextTurn()
	{
		turn = (turn+1)%2;
		String s = turn==0?"White":"Black";
		System.out.println("turn "+s);
	}
}
