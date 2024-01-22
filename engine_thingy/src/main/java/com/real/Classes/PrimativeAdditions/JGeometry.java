package com.real.Classes.PrimativeAdditions;

import com.real.Classes.Types.PhysicalObject2D;

public class JGeometry {
    // TODO add a shit ton of convenience methods for development.

    boolean IsObjectInsideObject(PhysicalObject2D object1, PhysicalObject2D object2) {

        

        // If one rectangle is on left side of other
        if (l1.x > r2.x || l2.x > r1.x)
            return false;

        // If one rectangle is above other
        if (l1.y < r2.y || l2.y < r1.y)
            return false;

        return true;
    }
}
