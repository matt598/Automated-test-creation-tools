import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.io.*;
public class DiscoverLocation extends JFrame implements KeyListener, Runnable
{
    static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    static Thread bounceThread;
    boolean record=true;
    boolean capture;
    static PrintWriter typer;

    public DiscoverLocation()
    {
        Container mainWindow = getContentPane();
        ColorPanel view = new ColorPanel(Color.WHITE);
        mainWindow.add(view);
        setSize(screenSize.width/10,screenSize.height/10);
        setVisible(true);
        addKeyListener(this);
    }

    public static void main(String[] args) throws InterruptedException
    {
        bounceThread = new Thread(new DiscoverLocation());
        bounceThread.start();
        try{
            typer = new PrintWriter (new File("clicks.txt"));
        }catch (Exception e) {}
    }

    public void run()

    {

        do
        {
            if(capture)
            {

                capture = false;
                repaint();
                try{
                    typer.print("("+MouseInfo.getPointerInfo().getLocation().x+", "+MouseInfo.getPointerInfo().getLocation().y+")");
                }catch (Exception e) {}
            }
            try
            {
                Thread.sleep(15);
            }catch (InterruptedException e) { }

            repaint();
        }while(record);
        typer.close();
    }

    public void keyPressed( KeyEvent e)
    {
        int c = e.getKeyCode();
        if(c == 29)
        {
            capture = true;
        }
        if(c == 76)
        {
            record = false;
        }
    }

    public void keyReleased(KeyEvent e)
    {
        int c = e.getKeyCode();
        if(c == 76)
        {
            record = false;
        }
    }

    public void keyTyped(KeyEvent e)
    {}

    public class ColorPanel extends JPanel
    {
        public ColorPanel( Color back)
        {
            setBackground(back);
        }

        public void paintComponent(Graphics g)
        {
            g.setColor(new Color(24,0,72));
            g.drawString(("("+MouseInfo.getPointerInfo().getLocation().x+", "+MouseInfo.getPointerInfo().getLocation().y+")"),screenSize.width/20, screenSize.height/20);  
        }
    }
}
