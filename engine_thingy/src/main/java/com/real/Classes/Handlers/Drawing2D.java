package com.real.Classes.Handlers;

// Imports

import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Vector;

import javax.imageio.ImageIO;

import com.real.Classes.Types.ObjectClass;
import com.real.Classes.Types.Particle2D;
import com.real.Classes.Types.PhysicalObject2D;
import com.real.Classes.Types.Vector2;

public class Drawing2D {
    // pre-defines because i suck at programming anything half memory efficient

    private static PhysicalObject2D Object2D;
    private static Particle2D particle;

    // Image Draw stuff

    protected static Object Hints = RenderingHints.VALUE_ANTIALIAS_ON;
    protected static RenderingHints.Key InterpolationStyle = RenderingHints.KEY_ANTIALIASING;

    // methods

    // random code off the internet that somehow worked.
    public static BufferedImage RotateImage (BufferedImage image, int n) { //n rotation in gradians
        double rotationRequired = Math.toRadians (n);
        double locationX = image.getWidth() / 2;
        double locationY = image.getHeight() / 2;
        AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);         
        BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType()); 
        op.filter(image, newImage);
        return newImage;
    }

    public static BufferedImage toCompatibleImage(BufferedImage image)
    {
        // obtain the current system graphical settings
        GraphicsConfiguration gfxConfig = GraphicsEnvironment.
        getLocalGraphicsEnvironment().getDefaultScreenDevice().
        getDefaultConfiguration();

        if (image.getColorModel().equals(gfxConfig.getColorModel()))
            return image;

        BufferedImage newImage = gfxConfig.createCompatibleImage(image.getWidth(), image.getHeight(), image.getTransparency());

        Graphics2D g2d = newImage.createGraphics();

        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();

        return newImage; 
    }

    public static BufferedImage drawFrame (Vector2 CameraPos, double CameraZoom, int width, int height) throws IOException {

        BufferedImage resultingFrame = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        Graphics2D frameGraphics = resultingFrame.createGraphics();

        frameGraphics.setRenderingHint(InterpolationStyle, Hints);

        // loopy time :)

        for (Object[] Element : ObjectClass.ObjectList) {

            if (Element[1] instanceof PhysicalObject2D) {

                Object2D = (PhysicalObject2D) (Array.get(Element, 1));
                BufferedImage img = toCompatibleImage(ImageIO.read(Object2D.GetTexture()));

                int Rotation = (int) ((PhysicalObject2D) Element[1]).GetRotation();

                if (Rotation != 0) {
                    img = RotateImage(img, Rotation);
                }

                int ImgWidth = (int) (Object2D.GetSize().GetX() * CameraZoom);
                int ImgHeight = (int) (Object2D.GetSize().GetY() * CameraZoom);

                int offsetX = (int) -((CameraPos.GetX()+Object2D.GetPosition().GetX())*CameraZoom) + (width/2) - (ImgWidth/2);
                int offsetY = (int) -((CameraPos.GetY()+Object2D.GetPosition().GetY())*CameraZoom) + (height/2) - (ImgHeight/2);
                    
                frameGraphics.drawImage(img, offsetX, offsetY, ImgWidth, ImgHeight, null);

            } else if (Element[1] instanceof Particle2D) {

                particle = (Particle2D) (Array.get(Element, 1));

                int offsetX = (int) -((CameraPos.GetX()+particle.GetPosition().GetX())*CameraZoom) + (width/2);
                int offsetY = (int) -((CameraPos.GetY()+particle.GetPosition().GetY())*CameraZoom) + (height/2);

                frameGraphics.setPaint(particle.GetColor());
                frameGraphics.drawRect(offsetX, offsetY, 1, 1);
                
            }

        }

        return resultingFrame;
    }
}
