package com.real.Classes.Handlers;

// Imports

import com.real.Classes.Types.*;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.real.Classes.Handlers.*;

import java.time.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ProgramLoop {

    // Configs

    static int WINDOW_WIDTH = 1200, WINDOW_HEIGHT = 800;

    // Constants

    public static final Window window = new Window(WINDOW_WIDTH, WINDOW_HEIGHT);
    public static final JLabel ImageLabel = window.GetImageLabel();

    // delta time stuff

    public static double fpsCap = 144;
    public static double upsCap = 80;

    // values i might want for actual progress

    public static double deltaTime = 0;
    private static boolean frameReady = true;
    private static boolean running = true;

    public static void run() {

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
                    BufferedImage BImage = Drawing2D.drawFrame(new Vector2(0,0), 1, WINDOW_WIDTH, WINDOW_HEIGHT);
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
