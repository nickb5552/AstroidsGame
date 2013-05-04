package astroidsnickb;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class Controller extends JComponent implements KeyListener, ActionListener, Runnable
{
    JFrame astroidField;
    int width = Toolkit.getDefaultToolkit().getScreenSize().width;
    int height = Toolkit.getDefaultToolkit().getScreenSize().height;
    Ship battleCruiser;
    Timer paintTicker;
    Timer astroidTicker;
    ArrayList<Astroid> astroidList;
    Image spaceImage;
    int ShipXpos;
    int ShipYpos;
    AffineTransform shipAffineTransform = new AffineTransform();
    Area shipArea = new Area();
    Area astroidArea;

    public static void main(String[] joe)
    {
        SwingUtilities.invokeLater(new Controller());
    }

    public void run()
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
        spaceImage = new ImageIcon(this.getClass().getResource("SpaceBG.jpg")).getImage();
    }
    
    public boolean collision(Area area1, Area area2)
    {
        Area arealclone = (Area) area1.clone();
        arealclone.intersect(area2);
        if (!arealclone.isEmpty())
        {
           return false; 
        } else 
        {
            return true;
        }
    }

    @Override
    public void paint(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(spaceImage, 0, 0, null);
        shipAffineTransform = battleCruiser.moveSelf();
        System.out.println(battleCruiser);
        //shipArea = shipArea.createTransformedArea(battleCruiser.moveSelf());
        System.out.println(collision(shipArea, shipArea));
        battleCruiser.paintSelf(g2);
        for (Astroid a : astroidList)
        {
            a.moveSelf();
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
        if (ae.getSource() == astroidTicker)
        {
            astroidList.add(new Astroid());
        }
        if (ae.getSource() == paintTicker)
        {
            repaint();
        }
    }
}
