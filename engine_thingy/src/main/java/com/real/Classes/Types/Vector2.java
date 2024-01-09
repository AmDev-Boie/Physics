package com.real.Classes.Types;

public class Vector2 {
    // Final Variables

    // Class Variables

    // Object Variables

    protected float x,y;

    // Constructor

    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    // Getter

    public float GetX() {
        return this.x;
    };

    public float GetY() {
        return this.y;
    };

    public float GetMagnitude() {
        float absMagnitude = (float) Math.abs(Math.pow(x, 2) - Math.pow(y, 2));
        float magnitude = (float) Math.abs(Math.pow(x, 2) - Math.pow(y, 2));

        if (absMagnitude == magnitude) {
            return magnitude;
        } else {
            return -absMagnitude;
        }
    };

    // Setter

    public void SetX(float Value) {
        this.x = Value;
    };

    public void SetY(float Value) {
        this.x = Value;
    };

    // Override

    @Override

    public String toString() {
        return this.x + ", " + this.y;
    };
}
