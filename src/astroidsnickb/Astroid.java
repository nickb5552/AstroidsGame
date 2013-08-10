package astroidsnickb;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

public class Astroid
{

    int width = Toolkit.getDefaultToolkit().getScreenSize().width;
    int height = Toolkit.getDefaultToolkit().getScreenSize().height;
    double astroidHeading;
    double astroidXpos;
    double astroidYpos;
    double astroidSpeed;
    double astroidXspeed;
    double astroidYspeed;
    double astroidSize;
    double astroidDeltaX;
    double astroidDeltaY;
    double scaleX;
    double scaleY;
    Ellipse2D.Double astroid;
    private AffineTransform astroidAffineTransform = new AffineTransform();
    private Area astroidArea;

    public Astroid() //Constructor 
    {
        astroidSize = (Math.random() * 50) + 5;
        astroidSpeed = (Math.random() * 5) + 2;
        int edge = (int) ((Math.random() * 4) + 1);
        switch (edge)
        {
            case 1:
                astroidXpos = Math.random() * width;
                astroidYpos = 0;
                astroidHeading = (Math.random() * 180) + 90;
                astroidDeltaX = Math.sin(Math.toRadians(astroidHeading)) * astroidSpeed;
                astroidDeltaY = -Math.cos(Math.toRadians(astroidHeading)) * astroidSpeed;
                break;
            case 2:
                astroidXpos = width;
                astroidYpos = Math.random() * height;
                astroidHeading = (Math.random() * 180) + 180;
                astroidDeltaX = Math.sin(Math.toRadians(astroidHeading)) * astroidSpeed;
                astroidDeltaY = -Math.cos(Math.toRadians(astroidHeading)) * astroidSpeed;
                break;
            case 3:
                astroidXpos = Math.random() * width;
                astroidYpos = height;
                astroidHeading = (Math.random() * 180) + 270;
                astroidDeltaX = Math.sin(Math.toRadians(astroidHeading)) * astroidSpeed;
                astroidDeltaY = -Math.cos(Math.toRadians(astroidHeading)) * astroidSpeed;
                break;
            case 4:
                astroidXpos = 0;
                astroidYpos = Math.random() * height;
                astroidHeading = (Math.random() * 180) + 0;
                astroidDeltaX = Math.sin(Math.toRadians(astroidHeading)) * astroidSpeed;
                astroidDeltaY = -Math.cos(Math.toRadians(astroidHeading)) * astroidSpeed;
                break;
        }
        astroid = new Ellipse2D.Double(astroidXpos, astroidYpos, astroidSize, astroidSize);
        astroidArea = new Area(astroid);
    }
    
    public void paintSelf(Graphics2D g2)
    {
        g2.setTransform(astroidAffineTransform);
        g2.setColor(new Color(0x8B, 0x45, 0x13));
        astroidXpos += astroidDeltaX;
        astroidYpos += astroidDeltaY;
        g2.translate(astroidXpos, astroidYpos);
        g2.scale(astroidSize, astroidSize);
        g2.fill(astroid);
        astroidAffineTransform = g2.getTransform();
    }

    public Area getAstroidArea()
    {
        return astroidArea;
    }

    public AffineTransform getAstroidAffineTransform()
    {
        return astroidAffineTransform;
    }
}
