package astroidsnickb;

import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

public class Astroid
{
    Ellipse2D.Double astroid;
    
    public Astroid()
    {
        astroid = new Ellipse2D.Double(500, 500, 50, 50);
    }

    public void moveSelf()
    {
    }

    public void paintSelf(Graphics2D g2)
    {
        g2.fill(astroid);
    }
}
