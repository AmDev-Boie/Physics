package com.real;

// Imports

import com.real.Classes.Types.*;
import com.real.Classes.Handlers.*;
import com.real.Classes.PrimativeAdditions.*;

public class Main {

    // Objects

    private static PhysicalObject2D object = new PhysicalObject2D();
    // private static Particle2D particle = new Particle2D();
    // private static Particle2D particle2 = new Particle2D();
    // private static Particle2D particle3 = new Particle2D();
    // private static Particle2D particle4 = new Particle2D();
    // private static Particle2D particle5 = new Particle2D();
    // private static Particle2D particle6 = new Particle2D();

    public static void main(String[] args) {

        object.SetVelocity(new Vector2(0,0)); // (float) (JMath.RandomBetween(-10, 10)),(float) (JMath.RandomBetween(0, 10))
        // particle.SetVelocity(new Vector2((float) (JMath.RandomBetween(-10, 10)),(float) (JMath.RandomBetween(0, 10))));
        // particle2.SetVelocity(new Vector2((float) (JMath.RandomBetween(-10, 10)),(float) (JMath.RandomBetween(0, 10))));
        // particle3.SetVelocity(new Vector2((float) (JMath.RandomBetween(-10, 10)),(float) (JMath.RandomBetween(0, 10))));
        // particle4.SetVelocity(new Vector2((float) (JMath.RandomBetween(-10, 10)),(float) (JMath.RandomBetween(0, 10))));
        // particle5.SetVelocity(new Vector2((float) (JMath.RandomBetween(-10, 10)),(float) (JMath.RandomBetween(0, 10))));
        // particle6.SetVelocity(new Vector2((float) (JMath.RandomBetween(-10, 10)),(float) (JMath.RandomBetween(0, 10))));

        ProgramLoop.run();

    }
}