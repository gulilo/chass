package mecanics;

import panels.Board;
import resorsces.API;

import javax.swing.*;
import java.awt.*;

public class Core
{
	public static API api;

	private Player[] players;
	private JPanel p;

	public Core(JPanel p, Dimension size, Point loc)
	{
		this.p = p;
		players = new Player[2];
		players[0] = new Player(0);
		players[1] = new Player(1);
		System.out.println("turn White");
		int tileSize = (int) Math.sqrt(size.width * size.height) / 8;
		Board board = new Board(size, loc, tileSize, players);
		api = new API(board, this);
		p.add(board);
		update();
	}

	public void gameOver()
	{
		System.out.println("done!");
	}

	public void update()
	{
		p.repaint();
	}

	public Player[] getPlayers()
	{
		return players;
	}
}
