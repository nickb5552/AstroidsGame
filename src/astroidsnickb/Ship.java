package astroidsnickb;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;

public class Ship
{
    int width = Toolkit.getDefaultToolkit().getScreenSize().width;
    int height = Toolkit.getDefaultToolkit().getScreenSize().height;
    double shipXpos = 950;
    double shipYpos = 800;
    double shipSpeed = 0;
    double shipDeltaX = 0;
    double shipDeltaY = 0;
    double shipHeading = 0;
    int[] xPoints =
    {
        0, 3, 1, 1, -1, -1, -3
    };
    int[] yPoints =
    {
        -4, 0, 2, 1, 1, 2, 0
    };
    Polygon shipShape = new Polygon(xPoints, yPoints, xPoints.length);

    public void moveSelf()
    {
        shipDeltaX = Math.sin(Math.toRadians(shipHeading)) * shipSpeed;
        shipDeltaY = -Math.cos(Math.toRadians(shipHeading)) * shipSpeed;
        shipXpos = shipXpos + shipDeltaX;
        shipYpos += shipDeltaY;
        if (shipYpos < 0)
        {
            shipYpos = height;
        }
        
        if (shipYpos > height)
        {
            shipYpos = 0;
        }
        
        if (shipXpos < 0)
        {
            shipXpos = width;
        }
         if (shipXpos > width)
        {
            shipXpos = 0;
        }
    }

    public void paintSelf(Graphics2D g2)
    {
        g2.drawString("Course " + shipHeading, 1800, 200);
        g2.drawString("Speed " + shipSpeed, 1800, 300);
        g2.setStroke(new BasicStroke(.01f));
        g2.setColor(Color.BLUE);
        g2.translate(shipXpos, shipYpos);
        g2.scale(20, 20);
        g2.rotate(Math.toRadians(shipHeading));
        g2.draw(shipShape);
        g2.setTransform(new AffineTransform());
    }
}
