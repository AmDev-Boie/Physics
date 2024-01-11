package com.real.Classes.Handlers;

import java.lang.reflect.Array;

import com.real.Classes.Types.*;
public class Physics2D {

    // Physics cosntants

    private static Vector2 gravitationalConstant = new Vector2((float) (0), (float) (-9.81));
    private static float drag = 50;
    private static float terminalVelocity = -20;
    protected static float simSpeed = 1;

    // more pre-defines because yet again im incompetent.

    private static Particle2D particle;
    private static PhysicalObject2D object;
    
    public static void stepPhysics(double deltatime) {
        for (Object[] Element : ObjectClass.ObjectList) {
            if (((ObjectClass) Element[1]).GetAnchored() == false) {
                if ((Element[1] instanceof Particle2D)) {
                particle = (Particle2D) (Array.get(Element, 1));

                // getting the particle properties so i dont gotta call the function every other nanosecond

                float mass = particle.GetMass();
                Vector2 position = particle.GetPosition();
                Vector2 velocity = particle.GetVelocity();

                // calculate velocity vector2d

                float velocityX = (float) (velocity.GetX() + ((gravitationalConstant.GetX())*deltatime))/drag;

                if (Math.abs(particle.GetVelocity().GetX()) <= Math.abs(terminalVelocity)) {
                    particle.SetVelocity(new Vector2(velocityX * simSpeed, velocity.GetY()));
                } else {
                    if (velocityX <= terminalVelocity) {
                        particle.SetVelocity(new Vector2(terminalVelocity * simSpeed, velocity.GetY()));
                    } else {
                        particle.SetVelocity(new Vector2(-terminalVelocity * simSpeed, velocity.GetY()));
                    }
                }

                float velocityY = (float) (velocity.GetY() + ((gravitationalConstant.GetY())*deltatime))/drag;

                if (Math.abs(particle.GetVelocity().GetY()) <= Math.abs(terminalVelocity)) {
                    particle.SetVelocity(new Vector2(velocity.GetX(), velocityY * simSpeed));
                } else {
                    if (velocityY <= terminalVelocity) {
                        particle.SetVelocity(new Vector2(velocity.GetX(), terminalVelocity * simSpeed));
                    } else {
                        particle.SetVelocity(new Vector2(velocity.GetX(), -terminalVelocity * simSpeed));
                    }
                }

                // Apply velocity to position
                particle.SetPosition(new Vector2((float) (position.GetX() + (particle.GetVelocity().GetX()*deltatime*simSpeed)), (float) (position.GetY() + (particle.GetVelocity().GetY()*deltatime*simSpeed))));

                System.out.println("\n" + particle.GetPosition());
                System.out.println(particle.GetVelocity());
                System.out.println(deltatime);

                } else if(Element[1] instanceof PhysicalObject2D) {
                    object = (PhysicalObject2D) (Array.get(Element, 1));

                    float mass = object.GetMass();
                    Vector2 position = object.GetPosition();
                    Vector2 velocity = object.GetVelocity();

                    float velocityX = (float) (velocity.GetX() + ((gravitationalConstant.GetX())*deltatime))/drag;

                    if (Math.abs(object.GetVelocity().GetX()) <= Math.abs(terminalVelocity)) {
                        object.SetVelocity(new Vector2(velocityX * simSpeed, velocity.GetY()));
                    } else {
                        if (velocityX <= terminalVelocity) {
                            object.SetVelocity(new Vector2(terminalVelocity * simSpeed, velocity.GetY()));
                        } else {
                            object.SetVelocity(new Vector2(-terminalVelocity * simSpeed, velocity.GetY()));
                        }
                    }

                    float velocityY = (float) (velocity.GetY() + ((gravitationalConstant.GetY())*deltatime))/drag;

                    if (Math.abs(object.GetVelocity().GetY()) <= Math.abs(terminalVelocity)) {
                        object.SetVelocity(new Vector2(velocity.GetX(), velocityY * simSpeed));
                    } else {
                        if (velocityY <= terminalVelocity) {
                            object.SetVelocity(new Vector2(velocity.GetX(), terminalVelocity*simSpeed));
                        } else {
                            object.SetVelocity(new Vector2(velocity.GetX(), -terminalVelocity*simSpeed));
                        }
                    }

                    object.SetPosition(new Vector2((float) (position.GetX() + (object.GetVelocity().GetX()*deltatime*simSpeed)), (float) (position.GetY() + (object.GetVelocity().GetY()*deltatime*simSpeed))));

                    System.out.println("\n" + object.GetPosition());
                    System.out.println(object.GetVelocity());
                    System.out.println(deltatime);
                }
            };
        };
    };

    // Getter

    public static float GetSimSpeed() {
        return simSpeed;
    }

    // Setter

    public static void SetSimSpeed(float Value) {
        simSpeed = Value;
    }

}
