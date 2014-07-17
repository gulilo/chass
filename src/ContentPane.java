import javax.swing.*;
import java.awt.*;

public class ContentPane extends JPanel {

	public ContentPane(Dimension size) {
		super();
		setSize(size);
		setLocation(0, 0);
		setLayout(null);

		int tileSize =(int)Math.sqrt((size.width*size.height*6)/10)/8;
		Board board = new Board(new Dimension(tileSize*8,tileSize*8),new Point(10,10),tileSize);
		add(board);
	}
}