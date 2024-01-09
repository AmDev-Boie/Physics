package com.real.Classes.Types;

import java.io.*;

public class PhysicalObject2D extends ObjectClass {

    protected Vector2 Position;
    protected Vector2 Size;
    protected EulerRotation Rotation;
    protected File Texture;
    protected int ZOrder;

    public PhysicalObject2D() {
        super("Object");

        this.Position = new Vector2(0,0);
        this.Size = new Vector2(50,50);
        this.Rotation = new EulerRotation(0);
        this.Texture = new File("/Users/27ayden.dillon/Desktop/Programs/TD_ENGINE/td_engine/src/main/resources/Images/Test.png");
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

    public void SetZOrder(int Value) {
        this.ZOrder = Value;
    };
}

