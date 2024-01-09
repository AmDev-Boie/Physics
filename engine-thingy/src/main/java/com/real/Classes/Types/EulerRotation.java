package com.real.Classes.Types;

public class EulerRotation {
    // Final Variables

    private final float MAX = 360;

    // Class Variables

    // Object Variables

    protected float r;

    // Constructor

    public EulerRotation(float Value) {
        this.r = Value % MAX;
    }

    // Getter

    public float ToFloat() {
        return this.r;
    };

    // Setter

    public void SetRotation(float Value) {
        this.r = Value % MAX;
    };

    // Override

    @Override

    public String toString() {
        return this.r + " Degrees";
    };
}
