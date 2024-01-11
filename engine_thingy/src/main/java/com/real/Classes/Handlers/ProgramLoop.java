package com.real.Classes.Handlers;

// Imports

import com.real.Classes.Types.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.real.Classes.Handlers.*;

import java.time.*;
import java.util.Vector;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ProgramLoop implements KeyListener {

    // Constants

    public static Window window;
    public static JLabel ImageLabel;

    public static int windowWidth;
    public static int windowHeight;

    // delta time stuff

    public static double fpsCap = 60;
    public static double upsCap = 40;

    // Camera Variables

    public static Vector2 CamPos = new Vector2(0, 0);
    public static double CamZoom = 0.5;

    // values i might want for actual progress

    public static double deltaTime = 0;
    private static boolean frameReady = true;
    private static boolean running = true;

    public static void initWindow(int WINDOW_WIDTH, int WINDOW_HEIGHT) {
        window = new Window(WINDOW_WIDTH, WINDOW_HEIGHT);
        ImageLabel = window.GetImageLabel();
        windowWidth = WINDOW_WIDTH;
        windowHeight = WINDOW_HEIGHT;

    }

    // Input shii
    // probably more efficient ways to do this, but for now ill do caveman ooga booga primate brain way.

    private static boolean A_HELD = false;
    private static boolean D_HELD = false;
    private static boolean W_HELD = false;
    private static boolean S_HELD = false;
    
    @Override
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();
    
        if (key == KeyEvent.VK_A) {
            A_HELD = true;
        }
    
        if (key == KeyEvent.VK_D) {
            D_HELD = true;
        }
    
        if (key == KeyEvent.VK_W) {
            W_HELD = true;
        }
    
        if (key == KeyEvent.VK_S) {
            S_HELD = true;
        }

        if (key == KeyEvent.VK_EQUALS) {
            CamZoom += 0.1;
        }

        if (key == KeyEvent.VK_MINUS) {
            CamZoom -= 0.1;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
    
        if (key == KeyEvent.VK_A) {
            A_HELD = false;
        }
    
        if (key == KeyEvent.VK_D) {
            D_HELD = false;
        }
    
        if (key == KeyEvent.VK_W) {
            W_HELD = false;
        }
    
        if (key == KeyEvent.VK_S) {
            S_HELD = false;
        }
    }

    // Actually run the thingy

        public static void run2D() {

            long initialTime = System.nanoTime();
            final double timeU = 1000000000 / upsCap;
            final double timeF = 1000000000 / fpsCap;
            double deltaU = 0, deltaF = 0;
            int frames = 0, ticks = 0;
            long timer = System.currentTimeMillis();

            while (running) {

                long currentTime = System.nanoTime();
                deltaU += (currentTime - initialTime) / timeU;
                deltaF += (currentTime - initialTime) / timeF;
                initialTime = currentTime;

                if (deltaU >= 1) {
                    Physics2D.stepPhysics(deltaU);
                    ticks++;
                    deltaU--;
                }

                if (deltaF >= 1) {
                    try {
                        // Check Inputs

                        if (A_HELD == true) {
                            CamPos.SetX((float) (CamPos.GetX() + (-10 * deltaF)));
                        }
                    
                        if (D_HELD == true) {
                            CamPos.SetX((float) (CamPos.GetX() + 10 * deltaF));
                        }
                    
                        if (W_HELD == true) {
                            CamPos.SetY((float) (CamPos.GetY() + -10 * deltaF));
                        }
                    
                        if (S_HELD == true) {
                            CamPos.SetY((float) (CamPos.GetY() + 10 * deltaF));
                        }

                        // Draw frame

                        BufferedImage BImage = Drawing2D.drawFrame(CamPos, CamZoom, windowWidth, windowHeight);
                        ImageLabel.setIcon(new ImageIcon(BImage));

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    frames++;
                    deltaF--;
                }

                if (System.currentTimeMillis() - timer > 1000) {
                    frames = 0;
                    ticks = 0;
                    timer += 1000;
                }
            }
        }
}
