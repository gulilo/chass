package mecanics;

import panels.Board;
import resorsces.API;

import java.awt.*;

public class Core
{
	public static API api;

	private Player[] players;
	private Screen screen;

	public Core()
	{
		screen = new Screen();
		screen.open();
		players = new Player[2];
		players[0] = new Player(0);
		players[1] = new Player(1);
		System.out.println("turn White");
		int tileSize = (int) Math.sqrt((screen.getSize().width * screen.getSize().height * 6) / 10) / 8;
		Board board = new Board(new Dimension(tileSize * 8 + 50, tileSize * 8 + 50), new Point(10, 10), tileSize, players);
		api = new API(board, this);
		screen.getContentPane().add(board);
		update();
	}

	public void gameOver()
	{
		//TODO make gameover
	}

	public void update()
	{
		screen.repaint();
	}

	public Player[] getPlayers()
	{
		return players;
	}
}
