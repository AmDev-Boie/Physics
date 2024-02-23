package com.real.Classes.Types;

import com.real.Classes.PrimativeAdditions.JMath;

public class Vector2 {
    // Final Variables

    // Class Variables

    // Object Variables

    protected double x,y;

    // Constructor

    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // Getter

    public double GetX() {
        return this.x;
    };

    public double GetY() {
        return this.y;
    };

    public double GetMagnitude() {
        double absMagnitude = Math.abs(Math.pow(x, 2) - Math.pow(y, 2));
        double magnitude = (Math.pow(x, 2) - Math.pow(y, 2));

        if (absMagnitude == magnitude) {
            return Math.sqrt(absMagnitude);
        } else {
            return -Math.sqrt(absMagnitude);
        }
    };

    // Setter

    public void SetX(double Value) {
        this.x = Value;
    };

    public void SetY(double Value) {
        this.y = Value;
    };

    // Override

    @Override
    public String toString() {
        return this.x + ", " + this.y;
    };

    public Vector2 RoundToDecimal(int decimalPrecision) {
        return new Vector2(JMath.RoundToDecimal(this.x, decimalPrecision), JMath.RoundToDecimal(this.y, decimalPrecision));
    };
}
