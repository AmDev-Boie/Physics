package com.real;

// Imports

import com.real.Classes.Types.*;

import java.util.concurrent.TimeUnit;

import com.real.Classes.Handlers.*;
import com.real.Classes.PrimativeAdditions.*;

public class Main {

    // Window Variables

    private static int windowWidth = 1200;
    private static int windowHeight = 800;

    // Simulation Variables

    private static int particleCount = 10;

    public static void main(String[] args) {
        for (int i = 0; i < particleCount; i++) {
            PhysicalObject2D object = new PhysicalObject2D();
            object.SetVelocity(new Vector2((float) JMath.RandomBetween(-5, 5), (float) JMath.RandomBetween(0, 10)));
            // object.SetPosition(new Vector2((float) JMath.RandomBetween(-100, 100), 1000));
        }
        
        ProgramLoop.initWindow(windowWidth, windowHeight);
        ProgramLoop.run2D();
    }
}