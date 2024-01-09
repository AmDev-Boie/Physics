package com.real.Classes.Handlers;

import java.lang.reflect.Array;

import com.real.Classes.Types.*;
public class Physics2D {

    // Physics cosntants

    private static Vector2 gravitationalConstant = new Vector2(0, (float) (-9.81));
    private static float drag = 1;

    // more pre-defines because yet again im incompetent.

    private static Particle2D particle;
    
    public static void stepPhysics(double deltatime) {
        for (Object[] Element : ObjectClass.ObjectList) {
            if (Element[1] instanceof Particle2D) {
                particle = (Particle2D) (Array.get(Element, 1));

                // getting the particle properties so i dont gotta call the function every other nanosecond

                Vector2 acceleration = particle.GetAcceleration();
                float mass = particle.GetMass();
                Vector2 position = particle.GetPosition();
                Vector2 velocity = particle.GetVelocity();

                // calculate the physical properties of this particle on this frame

                float weight = (mass*gravitationalConstant.GetMagnitude());

                float terminalVelocity = (float) -(Math.sqrt(Math.abs((weight*2)/drag)));

                // calculate acceleration vector2d

                float accelerationX = (float) ((weight-drag)/mass);

                if (accelerationX < gravitationalConstant.GetX()) {
                    particle.SetAcceleration(new Vector2(accelerationX,particle.GetAcceleration().GetX()));
                } else {
                    particle.SetAcceleration(new Vector2(gravitationalConstant.GetX(), particle.GetAcceleration().GetY()));
                }

                float accelerationY = (float) ((weight-drag)/mass);

                if (accelerationY < gravitationalConstant.GetY()) {
                    particle.SetAcceleration(new Vector2(particle.GetAcceleration().GetY(),accelerationY));
                } else {
                    particle.SetAcceleration(new Vector2(particle.GetAcceleration().GetX(), gravitationalConstant.GetY()));
                }

                // calculate velocity vector2d

                float velocityX = (float) (particle.GetVelocity().GetX() + ((particle.GetAcceleration().GetX()/terminalVelocity)/deltatime));

                if (Math.abs(particle.GetVelocity().GetX()) < Math.abs(terminalVelocity)) {
                    particle.SetVelocity(new Vector2(velocityX,particle.GetVelocity().GetY()));
                } else {
                    particle.SetVelocity(new Vector2(terminalVelocity, particle.GetVelocity().GetY()));
                }

                float velocityY = (float) (particle.GetVelocity().GetY() + ((particle.GetAcceleration().GetY()/terminalVelocity)/deltatime));

                if (Math.abs(particle.GetVelocity().GetY()) < Math.abs(terminalVelocity)) {
                    particle.SetVelocity(new Vector2(particle.GetVelocity().GetX(), velocityY));
                } else {
                    particle.SetVelocity(new Vector2(particle.GetVelocity().GetX(), terminalVelocity));
                }

                // Apply velocity to position
                particle.SetPosition(new Vector2((float) (position.GetX() + particle.GetVelocity().GetX()*deltatime), (float) (position.GetY() + particle.GetVelocity().GetY()*deltatime)));

                System.out.println("\n" + particle.GetPosition());
                System.out.println(particle.GetAcceleration());
                System.out.println(particle.GetVelocity());
                System.out.println(terminalVelocity);
                System.out.println(deltatime);
            }
        }
    }

}
