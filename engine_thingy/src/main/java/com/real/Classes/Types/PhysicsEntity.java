package com.real.Classes.Types;

public class PhysicsEntity extends ObjectClass {

    // Instance Variables

    protected boolean anchored;
    protected boolean canCollide;
    protected float bounciness;

    // Constructor

    public PhysicsEntity(String Name) {
        super(Name);

        this.anchored = false;
        this.canCollide = true;
        this.bounciness = 0.9f;
    };

    // Getters

    public boolean GetAnchored() {
        return this.anchored;
    };

    public boolean GetCanCollide() {
        return this.canCollide;
    };

    public float GetBounciness() {
        return this.bounciness;
    };

    // Setters

    public void SetAnchored(boolean Value) {
        this.anchored = Value;
    };

    public void SetCanCollide(boolean Value) {
        this.canCollide = Value;
    };

    public void SetBounciness(float Value) {
        this.bounciness = Value;
    };
    
}
