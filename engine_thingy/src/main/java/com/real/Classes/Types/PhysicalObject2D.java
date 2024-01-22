package com.real.Classes.Types;

import java.io.*;

public class PhysicalObject2D extends PhysicsEntity {

    protected Vector2 Position;
    protected Vector2 Size;
    protected EulerRotation Rotation;
    protected File Texture;
    protected int ZOrder;

    // Physics

    protected Vector2 velocity;
    protected float mass;

    public PhysicalObject2D() {
        super("Object2D");

        this.Position = new Vector2(0,0);
        this.Size = new Vector2(50,50);
        this.Rotation = new EulerRotation(0);
        this.Texture = new File("./engine_thingy/src/main/resources/Images/ohno.png"); // no more need for manual input :)

        // Physics

        this.velocity = new Vector2(0, 0);
        this.mass = (float) 1;
    }

    // getter methods

    public Vector2 GetPosition() {
        return this.Position;
    };

    public Vector2 GetSize() {
        return this.Size;
    };

    public float GetRotation() {
        return this.Rotation.ToFloat();
    };

    public File GetTexture() {
        return this.Texture;
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

    public void SetSize(Vector2 Value) {
        this.Size = Value;
    };

    public void SetRotation(float Value) {
        this.Rotation = new EulerRotation(Value);
    };

    public void SetTexture(File Value) {
        this.Texture = Value;
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

