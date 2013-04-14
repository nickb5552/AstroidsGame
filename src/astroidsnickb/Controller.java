package astroidsnickb;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class Controller extends JComponent implements KeyListener
{
    JFrame astroidField;
    int width = Toolkit.getDefaultToolkit().getScreenSize().width;
    int height = Toolkit.getDefaultToolkit().getScreenSize().height;
    Ship battleCruiser;

    public static void main(String[] args)
    {
        new Controller().getGoing();
    }

    private void getGoing()
    {
        astroidField = new JFrame("Astroids");
        astroidField.setVisible(true);
        astroidField.setSize(width, height);
        astroidField.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        battleCruiser = new Ship();
        astroidField.add(this);
        astroidField.addKeyListener(this);
    }
    
    @Override
    public void paint(Graphics g)
    {
        Graphics2D g2 = (Graphics2D)g;
        battleCruiser.paintSelf(g2);
        battleCruiser.moveSelf();
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent ke)
    {
       
    }

    @Override
    public void keyPressed(KeyEvent ke)
    {
        if (ke.getKeyCode() == 37)
        {
            battleCruiser.shipHeading -= 5;
        }
        
        if (ke.getKeyCode() == 39)
        {
            battleCruiser.shipHeading += 5;
        }
        
        if (ke.getKeyCode() == 38)
        {
            battleCruiser.shipSpeed += 1;
        }
    }

    @Override
    public void keyReleased(KeyEvent ke)
    {
    }
}

