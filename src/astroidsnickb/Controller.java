package astroidsnickb;

import java.applet.AudioClip;
import java.awt.Color;
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
import java.net.URL;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JApplet;
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
    ArrayList<Bullet> bulletList;
    Image spaceImage;
    double shipXpos;
    double shipYpos;
    double shipSpeed;
    double shipHeading;
    AffineTransform shipAffineTransform = new AffineTransform();
    Area shipArea = new Area();
    Area astroidArea = new Area();
    Area bulletArea = new Area();
//    URL fireSoundAddress = getClass().getResource("bullet.wav");
//    AudioClip fireFile = JApplet.newAudioClip(fireSoundAddress);

    public static void main(String[] joe)
    {
        SwingUtilities.invokeLater(new Controller());
    }

    public void run()
    {
        astroidList = new ArrayList<Astroid>();
        bulletList = new ArrayList<Bullet>();
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
            return true;
        } else
        {
            return false;
        }
    }

    @Override
    public void paint(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(spaceImage, 0, 0, null);
        g2.setTransform(new AffineTransform());
        g2.setColor(Color.WHITE);
        g2.drawString(astroidList.size() + "", 500, 500);
        this.shipXpos = battleCruiser.getShipXpos();
        this.shipYpos = battleCruiser.getShipYpos();
        this.shipHeading = battleCruiser.getShipHeading();
        shipSpeed = battleCruiser.getShipSpeed();
        battleCruiser.paintSelf(g2);
        shipArea = shipArea.createTransformedArea(shipAffineTransform);
        for (int i = 0; i < astroidList.size(); i++)
        {
            g2.setTransform(new AffineTransform());
            Astroid a = astroidList.get(i);
            g2.translate(a.astroidXpos, a.astroidYpos);
            a.paintSelf(g2);
            if (a.astroidXpos > width)
            {
                astroidList.remove(i);
            }
            if (a.astroidXpos < -1000)
            {
                astroidList.remove(i);
            }
            if (a.astroidYpos > height)
            {
                astroidList.remove(i);
            }
            if (a.astroidYpos < -1000)
            {
                astroidList.remove(i);
            }
        }
        for (int i = 0; i < bulletList.size(); i++)
        {
            g2.setTransform(new AffineTransform()); //idenity transform -> set to (0,0)
            Bullet b = bulletList.get(i);
            b.paintSelf(g2);
            if (b.bulletXpos > width)
            {
                bulletList.remove(i);
            }
            if (b.bulletXpos < -1000)
            {
                bulletList.remove(i);
            }
            if (b.bulletYpos > height)
            {
                bulletList.remove(i);
            }
            if (b.bulletYpos < -1000)
            {
                bulletList.remove(i);
            }
            bulletArea.createTransformedArea(b.getBulletAffineTransform());
            g2.setColor(Color.yellow);
            g2.draw(bulletArea);
            for (int j = 0; j < astroidList.size(); j++) //collision checker
            {
                Astroid a = astroidList.get(j);
                astroidArea = a.getAstroidArea();
                if (collision(bulletArea, astroidArea))
                {
                    astroidList.remove(j);
                }
                astroidArea.createTransformedArea(a.getAstroidAffineTransform());
                g2.setColor(Color.GREEN);
                g2.draw(astroidArea);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent ke)
    {
    }

    @Override
    public void keyPressed(KeyEvent ke)
    {
        if (ke.getKeyCode() == KeyEvent.VK_LEFT)//turns left
        {
            battleCruiser.setShipHeading(battleCruiser.getShipHeading() - 5);
        }

        if (ke.getKeyCode() == KeyEvent.VK_RIGHT)//turns right
        {
            battleCruiser.setShipHeading(battleCruiser.getShipHeading() + 5);
        }

        if (ke.getKeyCode() == KeyEvent.VK_UP)//increase speed
        {
            battleCruiser.setShipSpeed(battleCruiser.getShipSpeed() + 1);
        }
        if (ke.getKeyCode() == KeyEvent.VK_DOWN)//decrease speed
        {
            battleCruiser.setShipSpeed(battleCruiser.getShipSpeed() - 1);
        }
        if (ke.getKeyCode() == KeyEvent.VK_SPACE) //spacebar shoot bullet
        {
            bulletList.add(new Bullet(shipXpos, shipYpos, shipSpeed, shipHeading));
//            fireFile.play();
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
