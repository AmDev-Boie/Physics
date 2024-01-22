package com.real.Classes.Types;

public class PhysicsEntity extends ObjectClass {

    // Instance Variables

    protected boolean anchored;
    protected boolean canCollide;

    // Constructor

    public PhysicsEntity(String Name) {
        super(Name);

        this.anchored = false;
        this.canCollide = true;
    };

    // Getters

    public boolean GetAnchored() {
        return this.anchored;
    };

    public boolean GetCanCollide() {
        return this.canCollide;
    };

    // Setters

    public void SetAnchored(boolean Value) {
        this.anchored = Value;
    };

    public void SetCanCollide(boolean Value) {
        this.canCollide = Value;
    };
    
}
