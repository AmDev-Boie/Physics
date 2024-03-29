package com.real.Classes.Handlers;

import java.lang.reflect.Array;

import com.real.Classes.PrimativeAdditions.JGeometry;
import com.real.Classes.PrimativeAdditions.JMath;
import com.real.Classes.Types.*;

import java.awt.Rectangle;

public class Physics2D {

    // Physics cosntants

    private static Vector2 gravitationalConstant = new Vector2((float) (0), (float) (-9.81));
    private static double drag = 50;
    private static double terminalVelocity = -100;
    protected static double simSpeed = 1;

    // more pre-defines because yet again im incompetent.

    private static Particle2D particle;
    private static PhysicalObject2D object;
    
    public static void stepPhysics(double deltatime) {
        for (Object[] Element : ObjectClass.ObjectList) {
            if (((PhysicsEntity) Element[1]).GetAnchored() == false) {
                if ((Element[1] instanceof Particle2D)) {
                    particle = (Particle2D) (Array.get(Element, 1));

                    double mass = particle.GetMass();
                    Vector2 position = particle.GetPosition();
                    Vector2 velocity = particle.GetVelocity();

                    double velocityX = (velocity.GetX() + ((gravitationalConstant.GetX()/drag)*deltatime*simSpeed));

                    if (Math.abs(particle.GetVelocity().GetX()) <= Math.abs(terminalVelocity)) {
                        particle.SetVelocity(new Vector2(velocityX, velocity.GetY()));
                    } else {
                        if (velocityX <= terminalVelocity) {
                            particle.SetVelocity(new Vector2(terminalVelocity, velocity.GetY()));
                        } else {
                            particle.SetVelocity(new Vector2(-terminalVelocity, velocity.GetY()));
                        }
                    }

                    double velocityY = (velocity.GetY() + ((gravitationalConstant.GetY()/drag)*deltatime*simSpeed));

                    if (Math.abs(particle.GetVelocity().GetY()) <= Math.abs(terminalVelocity)) {
                        particle.SetVelocity(new Vector2(velocity.GetX(), velocityY));
                    } else {
                        if (velocityY <= terminalVelocity) {
                            particle.SetVelocity(new Vector2(velocity.GetX(), terminalVelocity));
                        } else {
                            particle.SetVelocity(new Vector2(velocity.GetX(), -terminalVelocity));
                        }
                    }

                    particle.SetPosition(new Vector2((position.GetX() + (((particle.GetVelocity().GetX()*deltatime)*simSpeed))/10), (position.GetY() + (((particle.GetVelocity().GetY()*deltatime)*simSpeed)/10))));

                } else if(Element[1] instanceof PhysicalObject2D) {
                    object = (PhysicalObject2D) (Array.get(Element, 1));

                    double mass = object.GetMass();
                    Vector2 position = object.GetPosition();
                    Vector2 velocity = object.GetVelocity();

                    double velocityX = (velocity.GetX() + ((gravitationalConstant.GetX()/drag)*deltatime*simSpeed));

                    if (Math.abs(object.GetVelocity().GetX()) <= Math.abs(terminalVelocity)) {
                        object.SetVelocity(new Vector2(velocityX, velocity.GetY()));
                    } else {
                        if (velocityX <= terminalVelocity) {
                            object.SetVelocity(new Vector2(terminalVelocity, velocity.GetY()));
                        } else {
                            object.SetVelocity(new Vector2(-terminalVelocity, velocity.GetY()));
                        }
                    }

                    double velocityY = (velocity.GetY() + ((gravitationalConstant.GetY()/drag)*deltatime*simSpeed));

                    if (Math.abs(object.GetVelocity().GetY()) <= Math.abs(terminalVelocity)) {
                        object.SetVelocity(new Vector2(velocity.GetX(), velocityY));
                    } else {
                        if (velocityY <= terminalVelocity) {
                            object.SetVelocity(new Vector2(velocity.GetX(), terminalVelocity));
                        } else {
                            object.SetVelocity(new Vector2(velocity.GetX(), -terminalVelocity));
                        }
                    }

                    for (Object[] Element2 : ObjectClass.ObjectList) {
                        if (Element2[1] instanceof PhysicalObject2D) {
                            PhysicalObject2D object2 = (PhysicalObject2D) (Array.get(Element2, 1));
                            if(object2 == object) {continue;} // makes sure the object isnt comparing to itself

                            if (object.GetCanCollide()) {
                                object.SetPosition(new Vector2((position.GetX() + (((object.GetVelocity().GetX()*deltatime)*simSpeed))/5), (position.GetY() + (((object.GetVelocity().GetY()*deltatime)*simSpeed)/5))));
                                if (JGeometry.IsObjectInsideObject(object, object2)) {

                                    float angle = (float) Math.atan(velocityY/velocityX);
                                    float angleMagnitude = (float) object.GetVelocity().GetMagnitude();

                                    object.SetPosition(new Vector2((position.GetX() - (((object.GetVelocity().GetX()*deltatime)*simSpeed))/10), (position.GetY() - (((object.GetVelocity().GetY()*deltatime)*simSpeed)/10))));
                                    object.SetVelocity(JMath.toVector(-angle, angleMagnitude * object.GetBounciness()));

                                    System.out.println("Bounce!");
                                } else {
                                    object.SetPosition(new Vector2((position.GetX() + (((object.GetVelocity().GetX()*deltatime)*simSpeed))/10), (position.GetY() + (((object.GetVelocity().GetY()*deltatime)*simSpeed)/10))));
                                }
                            }
                        }
                    }
                }
            };
        };
    };

    // Getter

    public static double GetSimSpeed() {
        return simSpeed;
    }

    // Setter

    public static void SetSimSpeed(double Value) {
        simSpeed = Value;
    }

}
