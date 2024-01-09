package com.real;

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

public class Main {

    // Objects

    private static PhysicalObject2D Particle = new PhysicalObject2D();

    public static void main(String[] args) {

        Particle.SetVelocity(new Vector2(0,0));

        ProgramLoop.run();

    }
}