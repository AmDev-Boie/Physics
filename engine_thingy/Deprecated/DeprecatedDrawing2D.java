// Deprecated Drawing2D class, pulled directly from the TD game engine, i kept it so i can reuse code to refit it to this specific case's needs.
// in short, this script is shit, ima rewrite it.

package com.real.Classes.Handlers;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;

import javax.imageio.ImageIO;

import com.real.Classes.Types.*;

public class DeprecatedDrawing2D {
    protected static int Width;
    protected static int Height;
    //private static int Pixels = Width*Height;

    private static PhysicalObject2D Object2D;

    // Image Draw stuff

    protected static Object Hints = RenderingHints.VALUE_ANTIALIAS_ON;
    protected static RenderingHints.Key InterpolationStyle = RenderingHints.KEY_ANTIALIASING;

    // Debug Border stuff

    protected static Boolean DebugSpriteBorders = false;
    protected static int BorderThickness = 1;

    // methods

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

    public static BufferedImage DrawFrame(Vector2 CameraPos, double CameraZoom, int width, int height) throws IOException {
        Width = width;
        Height = height;

        BufferedImage Frame = new BufferedImage(Width,Height,BufferedImage.TYPE_INT_RGB);
        Graphics2D FrameGraphics = Frame.createGraphics();

        FrameGraphics.setRenderingHint(InterpolationStyle, Hints);

        // Draw Background

        //Color BackgroundColor = new Color(31,24,38);
        //Color LineColor = new Color(35,26,43);

        //int LineStroke = 4;

        //int GridOffset_X = (int) -((LineStroke/2) + (Width/2) + (Tower.Default_Size.GetX()/2) - (CameraPos.GetX()));
        //int GridOffset_Y = (int) -((LineStroke/2) + (Height/2) + (Tower.Default_Size.GetY()/2) - (CameraPos.GetX()));

        // Fill Color

        //FrameGraphics.setPaint(BackgroundColor);
        //FrameGraphics.fillRect(0, 0, Frame.getWidth(), Frame.getHeight());

        // Draw Lines

        //FrameGraphics.setPaint (LineColor);

        //for (int i = 0; i < 20; i++) { // Horizontal Lines
        //    FrameGraphics.fillRect( 0, (int) ((Tower.Default_Size.GetX())*CameraZoom + GridOffset_Y), Frame.getWidth(), LineStroke);
        //}

        //for (int i = 0; i < 20; i++) { // Vertical Lines
        //    FrameGraphics.fillRect((int) ((Tower.Default_Size.GetX())*CameraZoom + GridOffset_X), 0, LineStroke, Frame.getHeight());
        //}

        for (Object[] Element : ObjectClass.ObjectList) {

            System.out.println(Element[1].getClass().getSimpleName());

            BufferedImage img = ImageIO.read(new File("/Users/27ayden.dillon/Desktop/Programs/TD_ENGINE/td_engine/src/main/resources/Images/Test.png"));

            if (Element[1] instanceof PhysicalObject2D) {
                
                if (Element[1] instanceof Tower) { // Draw Tower
                    TowerObj = (Tower) (Array.get(Element, 1));

                    BufferedImage Base_Texture = ImageIO.read(TowerObj.GetBaseTexture());
                    BufferedImage Cannon_Texture = ImageIO.read(TowerObj.GetCannonTexture());

                    // Variables

                    int Rotation = (int) ((Tower) Element[1]).GetRotation();

                    int ImgWidth = (int) (TowerObj.GetSize().GetX() * CameraZoom);
                    int ImgHeight = (int) (TowerObj.GetSize().GetY() * CameraZoom);

                    int TextureOffset_X = (int) -((CameraPos.GetX()+TowerObj.GetPosition().GetX())*CameraZoom) + (Width/2) - (ImgWidth/2);
                    int TextureOffset_Y = (int) -((CameraPos.GetY()+TowerObj.GetPosition().GetY())*CameraZoom) + (Height/2) - (ImgHeight/2);

                    // Base

                    FrameGraphics.drawImage(Base_Texture, TextureOffset_X, TextureOffset_Y, ImgWidth, ImgHeight, null);

                    // Cannon

                    if (Rotation != 0) {
                        Cannon_Texture = RotateImage(Cannon_Texture, Rotation);
                    }

                    FrameGraphics.drawImage(Cannon_Texture, TextureOffset_X, TextureOffset_Y, ImgWidth, ImgHeight, null);

                } else if(Element[1] instanceof Tile) { // Draw Tiles
                    TileObj = (Tile) (Array.get(Element, 1));

                    BufferedImage Texture = ImageIO.read(TowerObj.GetBaseTexture());

                    int ImgWidth = (int) (TowerObj.GetSize().GetX() * CameraZoom);
                    int ImgHeight = (int) (TowerObj.GetSize().GetY() * CameraZoom);

                    int TextureOffset_X = (int) -((CameraPos.GetX()+TowerObj.GetPosition().GetX())*CameraZoom) + (Width/2) - (ImgWidth/2);
                    int TextureOffset_Y = (int) -((CameraPos.GetY()+TowerObj.GetPosition().GetY())*CameraZoom) + (Height/2) - (ImgHeight/2);

                    FrameGraphics.drawImage(Texture, TextureOffset_X, TextureOffset_Y, ImgWidth, ImgHeight, null);

                } else { // Draw Generic Object
                    Object2D = (PhysicalObject2D) (Array.get(Element, 1));

                    img = ImageIO.read(Object2D.GetTexture());

                    int Rotation = (int) ((PhysicalObject2D) Element[1]).GetRotation();

                    if (Rotation != 0) {
                        img = RotateImage(img, Rotation);
                    }

                    int ImgWidth = (int) (Object2D.GetSize().GetX() * CameraZoom);
                    int ImgHeight = (int) (Object2D.GetSize().GetY() * CameraZoom);

                    int TextureOffset_X = (int) -((CameraPos.GetX()+Object2D.GetPosition().GetX())*CameraZoom) + (Width/2) - (ImgWidth/2);
                    int TextureOffset_Y = (int) -((CameraPos.GetY()+Object2D.GetPosition().GetY())*CameraZoom) + (Height/2) - (ImgHeight/2);

                    //if (DebugSpriteBorders.equals(true)) {
                    //    FrameGraphics.setColor(new Color(44, 158, 245));
                    //    FrameGraphics.drawRect(TextureOffset_X-BorderThickness, TextureOffset_Y-BorderThickness, ImgWidth+(BorderThickness*2), ImgHeight+(BorderThickness*2));
                    //}
                    
                    FrameGraphics.drawImage(img, TextureOffset_X, TextureOffset_Y, ImgWidth, ImgHeight, null);
                    
                    //for (int X = 0; X < ImgWidth; X++) {
                    //    for (int Y = 0; Y < ImgHeight; Y++) {
                    //        Color PixelColor = new Color(img.getRGB(X, Y), true);
                    //
                    //        FrameGraphics.setColor(new Color(PixelColor.getRed(), PixelColor.getGreen(), PixelColor.getBlue(), PixelColor.getAlpha())); // set color to obj color + any shading/lighting and other effects.
                    //
                    //        FrameGraphics.drawRect(X+TextureOffset_X, Y+TextureOffset_Y, 1, 1);
                    //    }
                    //}
                };

            //FrameGraphics.drawImage(img, 0, 0, (int) (Object2D.GetPosition().GetX() * CameraZoom / Pixels), (int) (Object2D.GetPosition().GetY() * CameraZoom / Pixels), null);
            }
        }
        FrameGraphics.dispose();

        return Frame;
    };

    // Setters

    public static void SetDebugSpriteBorders(Boolean Value) {
        DebugSpriteBorders = Value;
    };

    public static void SetBorderThickness(int Value) {
        BorderThickness = Value;
    };

    public static void SetHints(Object Value) {
        Hints = Value;
    };

    public static void SetInterpolationStyle(RenderingHints.Key Value) {
        InterpolationStyle = Value;
    };
}
