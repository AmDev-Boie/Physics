package com.real.Classes.Types;

import java.awt.Color;

public class Particle2D extends PhysicsEntity {

    protected Vector2 Position;
    protected Color Color;
    protected int ZOrder;

    // Physics

    protected Vector2 velocity;
    protected float mass;

    public Particle2D() {
        super("Particle");

        this.Position = new Vector2(0,0);
        this.Color = new Color(255,255,255);

        // Physics

        this.velocity = new Vector2(0, 0);
        this.mass = (float) 1;
    }

    // getter methods

    public Vector2 GetPosition() {
        return this.Position;
    };

    public Color GetColor() {
        return this.Color;
    };

    public int GetZOrder() {
        return this.ZOrder;
    };

    public Vector2 GetVelocity() {
        return this.velocity;
    };

    public float GetMass() {
        return this.mass;
    };

    // setter methods

    public void SetPosition(Vector2 Value) {
        this.Position = Value;
    };

    public void SetColor(Color Value) {
        this.Color = Value;
    };

    public void SetZOrder(int Value) {
        this.ZOrder = Value;
    };

    public void SetVelocity(Vector2 Value) {
        this.velocity = Value;
    };

    public void SetMass(float Value) {
        this.mass = Value;
    };
}

