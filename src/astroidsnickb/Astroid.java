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
    private double astroidXtranslation;
    private double astroidYtranslation;
    private double astroidInitialXtranslation;
    private double astroidInitialYtranslation;
    double astroidSpeed;
    double astroidXspeed;
    double astroidYspeed;
    double astroidSize;
    double astroidDeltaX;
    double astroidDeltaY;
    double scaleX;
    double scaleY;
    Ellipse2D.Double astroidShape;
    private AffineTransform astroidAffineTransform = new AffineTransform(); //idenity transform
    private Area astroidArea;

    public Astroid() //Constructor 
    {
        astroidSize = (Math.random() * 50) + 5;
        astroidSpeed = (Math.random() * 5) + 2;
        int edge = (int) ((Math.random() * 4) + 1);
        switch (edge)
        {
            case 1: //top
                astroidInitialXtranslation = Math.random() * width;
                astroidInitialYtranslation = 0;
                astroidHeading = (Math.random() * 180) + 90;
                break;
            case 2: //right
                astroidInitialXtranslation = width;
                astroidInitialYtranslation = Math.random() * height;
                astroidHeading = (Math.random() * 180) + 180;
                break;
            case 3: //bottom
                astroidInitialXtranslation = Math.random() * width;
                astroidInitialYtranslation = height;
                astroidHeading = (Math.random() * 180) + 270;
                break;
            case 4: //left
                astroidInitialXtranslation = 0;
                astroidInitialYtranslation = Math.random() * height;
                astroidHeading = (Math.random() * 180) + 0;
                break;
        }
        astroidDeltaX = Math.sin(Math.toRadians(astroidHeading)) * astroidSpeed;
        astroidDeltaY = -Math.cos(Math.toRadians(astroidHeading)) * astroidSpeed;
        astroidShape = new Ellipse2D.Double(0, 0, astroidSize, astroidSize);
        astroidArea = new Area(astroidShape);
        astroidAffineTransform.setToTranslation(astroidInitialXtranslation, astroidInitialYtranslation);
    }

    public void paintSelf(Graphics2D g2)
    {
        g2.setTransform(getAstroidAffineTransform());
        g2.setColor(new Color(0x8B, 0x45, 0x13));
        //astroidSize *= 1.000000000000000001;
        //g2.scale(astroidSize, astroidSize);
        g2.fill(astroidArea);
        astroidXtranslation += astroidDeltaX;
        astroidYtranslation += astroidDeltaY;
        g2.translate(astroidDeltaX, astroidDeltaY);
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

    public double getAstroidXtranslation()
    {
        return astroidXtranslation;
    }

    public double getAstroidYtranslation()
    {
        return astroidYtranslation;
    }
}
