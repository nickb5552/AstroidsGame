package astroidsnickb;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;

public class Ship
{
    int shipXpos = 950;
    int shipYpos = 800;
    int shipSpeed = 0;
    double shipDeltaX = 0;
    double shipDeltaY = 0;
    int shipHeading = 0;
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
        shipDeltaX = Math.sin(shipHeading) * shipSpeed;
        shipDeltaY = -Math.cos(shipHeading) * shipSpeed;
        shipXpos = (int) (shipXpos + shipDeltaX);
        shipYpos += shipDeltaY;
    }

    public void paintSelf(Graphics2D g2)
    {
        g2.setStroke(new BasicStroke(.01f));
        g2.setColor(Color.BLUE);
        g2.translate(shipXpos, shipYpos);
        g2.scale(20, 20);
        g2.rotate(Math.toRadians(shipHeading));
        g2.draw(shipShape);
    }
}
