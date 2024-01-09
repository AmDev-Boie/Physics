package com.real.Classes.Handlers;

import java.lang.reflect.Array;

import com.real.Classes.Types.*;
public class Physics2D {

    // Physics cosntants

    private static Vector2 gravitationalConstant = new Vector2(0, (float) (-9.81));
    private static float drag = 50;

    // more pre-defines because yet again im incompetent.

    private static Particle2D particle;
    
    public static void stepPhysics(double deltatime) {
        for (Object[] Element : ObjectClass.ObjectList) {
            if ((Element[1] instanceof Particle2D) || (Element[1] instanceof PhysicalObject2D)) {
                particle = (Particle2D) (Array.get(Element, 1));

                // getting the particle properties so i dont gotta call the function every other nanosecond

                float mass = particle.GetMass();
                Vector2 position = particle.GetPosition();
                Vector2 velocity = particle.GetVelocity();

                float terminalVelocity = -6;

                // calculate velocity vector2d

                float velocityX = (float) (velocity.GetX() + ((gravitationalConstant.GetX()/drag)*deltatime));

                if (Math.abs(particle.GetVelocity().GetX()) <= Math.abs(terminalVelocity)) {
                    particle.SetVelocity(new Vector2(velocityX, velocity.GetY()));
                } else {
                    float tVs;

                    if (velocityX <= terminalVelocity) {
                        tVs = terminalVelocity;
                    } else {
                        tVs = -terminalVelocity;
                    }

                    particle.SetVelocity(new Vector2(tVs, velocity.GetY()));
                }

                float velocityY = (float) (velocity.GetY() + ((gravitationalConstant.GetY()/drag)*deltatime));

                if (Math.abs(particle.GetVelocity().GetY()) <= Math.abs(terminalVelocity)) {
                    particle.SetVelocity(new Vector2(velocity.GetX(), velocityY));
                } else {
                    float tVs;

                    if (velocityY <= terminalVelocity) {
                        tVs = terminalVelocity;
                    } else {
                        tVs = -terminalVelocity;
                    }

                    particle.SetVelocity(new Vector2(velocity.GetX(), tVs));
                }

                // Apply velocity to position
                particle.SetPosition(new Vector2((float) (position.GetX() + particle.GetVelocity().GetX()*deltatime), (float) (position.GetY() + particle.GetVelocity().GetY()*deltatime)));

                System.out.println("\n" + particle.GetPosition());
                System.out.println(particle.GetVelocity());
                System.out.println(deltatime);
            }
        }
    }

}
