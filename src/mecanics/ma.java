package mecanics;

import javax.swing.*;
import java.awt.*;

public class ma
{
	public static void main(String[] arr)
	{
		Screen s = new Screen();
		s.open();
		new Core((JPanel) s.getContentPane(), new Dimension(500, 500), new Point(10, 10));
	}
}
