package astroidsnickb;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.Timer;

public class Controller extends JComponent implements KeyListener, ActionListener
{
    JFrame astroidField;
    int width = Toolkit.getDefaultToolkit().getScreenSize().width;
    int height = Toolkit.getDefaultToolkit().getScreenSize().height;
    Ship battleCruiser;
    Timer paintTicker;
    Timer astroidTicker;
    ArrayList<Astroid> astroidList;

    public static void main(String[] args)
    {
        new Controller().getGoing();
    }

    private void getGoing()
    {
        astroidList = new ArrayList<Astroid>();
        paintTicker = new Timer(20, this);
        paintTicker.start();
        astroidTicker = new Timer(1000, this);
        astroidTicker.start();
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
        Graphics2D g2 = (Graphics2D) g;
        battleCruiser.moveSelf();
        battleCruiser.paintSelf(g2);
        for(Astroid a : astroidList) 
        {
            a.paintSelf(g2);
        }
    }

    @Override
    public void keyTyped(KeyEvent ke)
    {
    }

    @Override
    public void keyPressed(KeyEvent ke)
    {
        if (ke.getKeyCode() == 37)//turns left
        {
            battleCruiser.shipHeading -= 5;
        }

        if (ke.getKeyCode() == 39)//turns right
        {
            battleCruiser.shipHeading += 5;
        }

        if (ke.getKeyCode() == 38)//increase speed
        {
            battleCruiser.shipSpeed += 1;
        }
        if (ke.getKeyCode() == 40)//decrease speed
        {
            battleCruiser.shipSpeed -= 1;
        }
    }

    @Override
    public void keyReleased(KeyEvent ke)
    {
    }

    @Override
    public void actionPerformed(ActionEvent ae)
    {
        repaint();
        if (ae.getSource() == astroidTicker)
        {
            astroidList.add(new Astroid());
        }
    }
}
