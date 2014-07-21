package mecanics;

import panels.ContentPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.StringTokenizer;

public class Screen extends JFrame
{
    private final String FILENAME = "Screen.config";
    private final Dimension DEFAULT_SIZE = new Dimension(500,500);
    private final Point DEFAULT_LOCATION = new Point(200,200);

	private boolean open;

    public Screen()
    {
	    super("guli's chess game!");
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
               super.windowClosing(e);
               close();
            }
        });
        setResizable(false);
        setUndecorated(false);
        setLayout(null);

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                e.consume();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                e.consume();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
                {
	                close();
                }
            }
        });
    }

    private void saveStatus() {
        Dimension d = getSize();
        Point p = getLocation();
        try {
            FileWriter fw = new FileWriter(FILENAME);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write("Screen_size "+d.width +" "+d.height+"\n");
            bw.write("Screen_Location "+p.x +" "+p.y+"\n");

            bw.close();
            fw.close();
        } catch (IOException e) {
            System.err.println("cant save\n");
            e.printStackTrace();
        }
    }

    private void readStatus()
    {
        Dimension size = DEFAULT_SIZE;
        Point loc = DEFAULT_LOCATION;
        try
        {
            FileReader fr = new FileReader(FILENAME);
            BufferedReader br = new BufferedReader(fr);

            String s = br.readLine();
            StringTokenizer token = new StringTokenizer(s);
            token.nextToken();
            if(token.countTokens() >= 2) {
                try {
                    int width = Integer.parseInt(token.nextToken());
                    int height = Integer.parseInt(token.nextToken());
                    size = new Dimension(width, height);
                } catch (NumberFormatException ex) {
                    System.out.println("file corrupted");
                    ex.printStackTrace();
                }
            }

            s = br.readLine();
            token = new StringTokenizer(s);
            token.nextToken();
            if(token.countTokens() >= 2) {
                try {
                    int x = Integer.parseInt(token.nextToken());
                    int y = Integer.parseInt(token.nextToken());
                    loc = new Point(x, y);
                } catch (NumberFormatException ex) {
                    System.out.println("file corrupted");
                    ex.printStackTrace();
                }
            }
            br.close();
            fr.close();
        }
        catch (IOException e)
        {
            System.out.println("cant reed file, using default");
        }
        setSize(size);
        setLocation(loc);
    }

    public void open()
    {
        open = true;
        setVisible(true);
        readStatus();
        ContentPane p = new ContentPane(getSize());
        setContentPane(p);
    }


    public void close()
    {
	    open = false;
        saveStatus();
        setVisible(false);
        dispose();
    }

	public boolean isOpen() {
		return open;
	}
}
