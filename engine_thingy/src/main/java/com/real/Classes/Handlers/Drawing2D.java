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
import javax.swing.JFrame;

import com.real.Main;
import com.real.Classes.Types.*;

public class Drawing2D {
    // pre-defines because i suck at programming anything half memory efficient

    private static PhysicalObject2D Object2D;
    private static Particle2D particle;

    // Image Draw stuff

    protected static Object Hints = RenderingHints.VALUE_ANTIALIAS_ON;
    protected static RenderingHints.Key InterpolationStyle = RenderingHints.KEY_ANTIALIASING;

    // overlay stuff

    protected static boolean debugOverlay = false;
    protected static boolean controlOverlay = true;
    protected static int debugVelocityLineMagnitude = 8;

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

    // more random code off the internet that keeps working.
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

    // i like comments :)

    private static JFrame windowJFrame = ProgramLoop.windowJFrame;

    public static Point GetMousePoint() {
        Point p = MouseInfo.getPointerInfo().getLocation();
        p = new Point(p.x - windowJFrame.getLocation().x, p.y - windowJFrame.getLocation().y - windowJFrame.getInsets().top);
        return p;
    }

    public static BufferedImage drawFrame (Vector2 CameraPos, double CameraZoom, int width, int height) throws IOException {

        BufferedImage resultingFrame = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        Graphics2D frameGraphics = resultingFrame.createGraphics();

        frameGraphics.setRenderingHint(InterpolationStyle, Hints);

        // Draw background (silly lil rectangle)
        
        frameGraphics.setColor(new Color(86, 104, 133));
        frameGraphics.fillRect(-5, -5, width + 10, height + 10);

        // Draw Renderable Objects

        for (Object[] Element : ObjectClass.ObjectList) {

            if (Element[1] instanceof PhysicalObject2D) {

                // draw object

                Object2D = (PhysicalObject2D) (Array.get(Element, 1));
                BufferedImage img = toCompatibleImage(ImageIO.read(Object2D.GetTexture()));

                int Rotation = (int) ((PhysicalObject2D) Element[1]).GetRotation();

                if (Rotation%360 != 0) {
                    img = RotateImage(img, Rotation);
                }

                int ImgWidth = (int) (Object2D.GetSize().GetX() * CameraZoom);
                int ImgHeight = (int) (Object2D.GetSize().GetY() * CameraZoom);

                int offsetX = (int) -(((CameraPos.GetX()*10)+(Object2D.GetPosition().GetX()*10))*CameraZoom) + (width/2) - (ImgWidth/2);
                int offsetY = (int) -(((CameraPos.GetY()*10)+(Object2D.GetPosition().GetY()*10))*CameraZoom) + (height/2) - (ImgHeight/2);
                    
                frameGraphics.drawImage(img, offsetX, offsetY, ImgWidth, ImgHeight, null);

            } else if (Element[1] instanceof Particle2D) {

                particle = (Particle2D) (Array.get(Element, 1));

                int offsetX = (int) -(((CameraPos.GetX()*10)+(particle.GetPosition().GetX()*10))*CameraZoom) + (width/2);
                int offsetY = (int) -(((CameraPos.GetY()*10)+(particle.GetPosition().GetY()*10))*CameraZoom) + (height/2);

                frameGraphics.setColor(particle.GetColor());
                frameGraphics.drawRect(offsetX, offsetY, 1, 1);
                
            }

        }

        // Draw Hover Overlay

        boolean drewInfosheet = false;

        for(Object[] Element : ObjectClass.ObjectList) {
            if (drewInfosheet) {
                break;
            }

            if(Element[1] instanceof PhysicalObject2D) {
                Object2D = (PhysicalObject2D) (Array.get(Element, 1));

                Point mousePoint = GetMousePoint();

                int ImgWidth = (int) (Object2D.GetSize().GetX() * CameraZoom);
                int ImgHeight = (int) (Object2D.GetSize().GetY() * CameraZoom);

                int offsetX = (int) -(((CameraPos.GetX()*10)+(Object2D.GetPosition().GetX()*10))*CameraZoom) + (width/2) - (ImgWidth/2);
                int offsetY = (int) -(((CameraPos.GetY()*10)+(Object2D.GetPosition().GetY()*10))*CameraZoom) + (height/2) - (ImgHeight/2);

                // Overlay specific variables

                int VelocityoffsetX = (int) -(((CameraPos.GetX()*10)+(Object2D.GetPosition().GetX()*10) + (Object2D.GetVelocity().GetX() * debugVelocityLineMagnitude))*CameraZoom) + (width/2) - (ImgWidth/2);
                int VelocityoffsetY = (int) -(((CameraPos.GetY()*10)+(Object2D.GetPosition().GetY()*10) + (Object2D.GetVelocity().GetY() * debugVelocityLineMagnitude))*CameraZoom) + (height/2) - (ImgHeight/2);

                if(((mousePoint.getX() >= (offsetX) & mousePoint.getX() <= (offsetX + (ImgWidth))) &
                (mousePoint.getY() >= (offsetY) & mousePoint.getY() <= (offsetY + (ImgHeight)))) &
                debugOverlay) {
                    frameGraphics.setColor(new Color(0,255,0,255));
                    frameGraphics.setStroke(new BasicStroke((float) (4*CameraZoom)));
                    frameGraphics.drawLine(offsetX + (ImgWidth/2), offsetY + (ImgHeight/2), (int) ((VelocityoffsetX + (ImgWidth/2))), (int) ((VelocityoffsetY + (ImgHeight/2))));
                    frameGraphics.setStroke(new BasicStroke(1));

                    frameGraphics.setColor(new Color(0,0,0,100));

                    int hoverMenuOffsetX = (int) mousePoint.getX() + 10;
                    int hoverMenuOffsetY = (int) mousePoint.getY() + 10;

                    frameGraphics.fillRect(hoverMenuOffsetX, hoverMenuOffsetY, 250, 300);

                    frameGraphics.setFont(frameGraphics.getFont().deriveFont(25));

                    frameGraphics.setColor(new Color(255,255,255,255));
                    
                    frameGraphics.setFont(new Font("TimesRoman", Font.PLAIN, 13));
                    frameGraphics.drawString(Object2D.GetName() + " information", hoverMenuOffsetX + 5, hoverMenuOffsetY + 20);

                    frameGraphics.setFont(new Font("TimesRoman", Font.PLAIN, 10));
                    frameGraphics.drawString("Position: " + Object2D.GetPosition().RoundToDecimal(2), hoverMenuOffsetX + 5, hoverMenuOffsetY + 50);
                    frameGraphics.drawString("Size: " + Object2D.GetSize().RoundToDecimal(2), hoverMenuOffsetX + 5, hoverMenuOffsetY + 70);
                    frameGraphics.drawString("Velocity: " + Object2D.GetVelocity().RoundToDecimal(2), hoverMenuOffsetX + 5, hoverMenuOffsetY + 90);
                    frameGraphics.drawString("Rotation: " + Object2D.GetRotation(), hoverMenuOffsetX + 5, hoverMenuOffsetY + 110);
                    frameGraphics.drawString("Mass: " + Object2D.GetMass(), hoverMenuOffsetX + 5, hoverMenuOffsetY + 130);
                    
                    drewInfosheet = true;
                }
            }
        }

        // Draw Overlay

        frameGraphics.setColor(new Color(0,0,0,100));
        frameGraphics.fillRect(0, 0, width, 30);
        frameGraphics.setColor(new Color(255,255,255,255));
        frameGraphics.setFont(new Font("TimesRoman", Font.PLAIN, 15));
        frameGraphics.drawString("[1: Debug Menu] [2: nothing] [3: nothing] [4: nothing] [5: nothing] [6: nothing] [7: nothing] [8: nothing] [9: nothing] [0: Controls]", 10, 15);

        int overlaysOpened = 0;
        int overlayOffsetY = 100;

        if(controlOverlay) {
            frameGraphics.setColor(new Color(0,0,0,100));

            frameGraphics.fillRect(0, 30 + overlayOffsetY, 250, 200);

            frameGraphics.setColor(new Color(255,255,255,255));

            frameGraphics.setFont(new Font("TimesRoman", Font.PLAIN, 13));
            frameGraphics.drawString("-- Controls --", 10, 50 + overlayOffsetY);
            frameGraphics.setFont(new Font("TimesRoman", Font.PLAIN, 10));
            frameGraphics.drawString("Pan camera: W A S D", 5, 70 + overlayOffsetY);
            frameGraphics.drawString("Zoom camera: - =", 5, 90 + overlayOffsetY);
            frameGraphics.drawString("Change Simulation Speed: [ ]", 5, 110 + overlayOffsetY);

            overlaysOpened++;
        }

        if(debugOverlay) {

            frameGraphics.setColor(new Color(0,0,0,100));

            frameGraphics.fillRect(0, ((230*overlaysOpened) + overlayOffsetY) + 30, 250, 200);

            frameGraphics.setColor(new Color(255,255,255,255));

            frameGraphics.setFont(new Font("TimesRoman", Font.PLAIN, 13));
            frameGraphics.drawString("-- Graphics --", 10, 50 + ((230*overlaysOpened) + overlayOffsetY));
            frameGraphics.setFont(new Font("TimesRoman", Font.PLAIN, 10));
            frameGraphics.drawString("CamPos: " + Math.floor(CameraPos.GetX()) + ", " + Math.floor(CameraPos.GetY()), 15, 70 + ((230*overlaysOpened) + overlayOffsetY));
            frameGraphics.drawString("CamZoom: " + Math.floor(CameraZoom*100)/100, 15, 90 + ((230*overlaysOpened) + overlayOffsetY));

            frameGraphics.setFont(new Font("TimesRoman", Font.PLAIN, 13));
            frameGraphics.drawString("-- Physics --", 10, 140 + ((230*overlaysOpened) + overlayOffsetY));
            frameGraphics.setFont(new Font("TimesRoman", Font.PLAIN, 10));
            frameGraphics.drawString("SimSpeed: " + Math.floor(Physics2D.simSpeed*100)/100, 15, 160 + ((230*overlaysOpened) + overlayOffsetY));

            overlaysOpened++;
        }

        // Crosshair in middle of screen

        frameGraphics.drawRect((width/2)-4, (height/2), 9, 1);
        frameGraphics.drawRect((width/2), (height/2)-4, 1, 9);

        return resultingFrame;
    }

    // Getter

    public static boolean GetDebugOverlayStatus() {
        return debugOverlay;
    }

    public static boolean GetControlOverlayStatus() {
        return controlOverlay;
    }

    // Setter

    public static void SetDebugOverlayStatus(boolean value) {
        debugOverlay = value;
    }

    public static void SetControlOverlayStatus(boolean value) {
        controlOverlay = value;
    }
}
