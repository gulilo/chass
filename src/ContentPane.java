import javax.swing.*;
import java.awt.*;

public class ContentPane extends JPanel {

	private Board board;

	public ContentPane(Dimension size) {
		super();
		setSize(size);
		setLocation(0, 0);
		setLayout(null);

		int tileSize =(int)Math.sqrt((size.width*size.height*6)/10)/8;
		board = new Board(tileSize);
		board.addBoard(this);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getSize().width, getSize().height);
		board.drawBoard(g);
	}
}