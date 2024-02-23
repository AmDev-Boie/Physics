package com.real.Classes.Types;

import com.real.Classes.PrimativeAdditions.JMath;

public class JPolygon {
    // Final Variables

    // Class Variables

    // Object Variables

    protected Vector2 originPosition;
    protected Vector2[] vertices;

    // Constructor

    public JPolygon() {
        originPosition = new Vector2(0, 0);

        vertices = new Vector2[3];
        vertices[0] = new Vector2(0, 0);
        vertices[1] = new Vector2(0, 1);
        vertices[2] = new Vector2(1, 1);
        vertices[3] = new Vector2(1, 0);
    }

    // Getter

    public Vector2[] GetVertices() {
        return this.vertices;
    };

    public Vector2 GetPosition() {
        return this.originPosition;
    };

    // Setter

    public void SetPosition(Vector2 Value) {
        this.originPosition = Value;
    };

    public void SetVertices(Vector2[] Value) {
        this.vertices = Value;
    };

    // Override
}
