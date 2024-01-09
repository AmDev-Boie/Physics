package com.real;

// Imports

import com.real.Classes.Types.*;

import java.util.concurrent.TimeUnit;

import com.real.Classes.Handlers.*;
import com.real.Classes.PrimativeAdditions.*;

public class Main {

    private static int particleCount = 60;

    public static void main(String[] args) {
        for (int i = 0; i < particleCount; i++) {
            Particle2D object = new Particle2D();
            object.SetVelocity(new Vector2((float) JMath.RandomBetween(-5, 5), (float) JMath.RandomBetween(0, 10)));
        }

        ProgramLoop.run2D();
    }
}