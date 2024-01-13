package com.real;

// Imports

import com.real.Classes.Types.*;

import java.util.concurrent.TimeUnit;

import com.real.Classes.Handlers.*;
import com.real.Classes.PrimativeAdditions.*;

public class Main{

    // Window Variables

    private static int windowWidth = 1400;
    private static int windowHeight = 1000;

    // Simulation Variables

    private static int particleCount = 1;

    // Override Drawing2D's UI method to add some UI
    // REQUIRED

    public static void main(String[] args) {

        // Gravity Test

        for (int i = 0; i < particleCount; i++) {
            PhysicalObject2D object = new PhysicalObject2D();
            object.SetVelocity(new Vector2((float) JMath.RandomBetween(-5, 5), (float) JMath.RandomBetween(0, 10)));
            object.SetPosition(new Vector2(0, 20));
        }

        // Collision Test
        /*
        PhysicalObject2D anchoredObject = new PhysicalObject2D();
        anchoredObject.SetAnchored(true);

        PhysicalObject2D collisionObject = new PhysicalObject2D();
        collisionObject.SetPosition(new Vector2(0, 200));
        */
        Physics2D.SetSimSpeed((float) (0));
        
        ProgramLoop.initWindow(windowWidth, windowHeight);
        ProgramLoop.run2D();
    }
}