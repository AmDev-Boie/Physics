package com.real.Classes.PrimativeAdditions;

import com.real.Classes.Types.PhysicalObject2D;
import com.real.Classes.Types.Vector2;

import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.Vector;

public class JGeometry {
    // TODO add a shit ton of convenience methods for development.

    public static boolean IsObjectInsideObject(PhysicalObject2D object1, PhysicalObject2D object2) { // just so i dont need to litter rectangle declerations all over.
        Rectangle r = new Rectangle((int) object1.GetPosition().GetX(), (int) object1.GetPosition().GetY(), (int) object1.GetSize().GetX(), (int) object1.GetSize().GetY());
        Rectangle p = new Rectangle((int) object2.GetPosition().GetX(), (int) object2.GetPosition().GetY(), (int) object2.GetSize().GetX(), (int) object2.GetSize().GetY());

        return r.intersects(p);
    }

    public static Rectangle GetIntersectionOfObjects(PhysicalObject2D object1, PhysicalObject2D object2) {
        Rectangle r = new Rectangle((int) object1.GetPosition().GetX(), (int) object1.GetPosition().GetY(), (int) object1.GetSize().GetX(), (int) object1.GetSize().GetY());
        Rectangle p = new Rectangle((int) object2.GetPosition().GetX(), (int) object2.GetPosition().GetY(), (int) object2.GetSize().GetX(), (int) object2.GetSize().GetY());

        return r.intersection(p);
    }

    public static boolean IsPolygonInsidePolygon(Polygon poly1, Polygon poly2) {
        Vector2[] CoordinatePoints1 = new Vector2[poly1.npoints];
        Vector2[] CoordinatePoints2 = new Vector2[poly2.npoints];

        for(int i = 0; i < poly1.npoints; i++) {
            CoordinatePoints1[i] = new Vector2(poly1.xpoints[i], poly1.ypoints[i]);
        }

        for(int i = 0; i < poly2.npoints; i++) {
            CoordinatePoints2[i] = new Vector2(poly2.xpoints[i], poly2.ypoints[i]);
        }

        for(Vector2 vector : CoordinatePoints1) {
            // do smthn with the seperating axis theorem.

            Vector2 normalVector = new Vector2(vector.GetY(), -vector.GetX());

            
        }

        return false;
    }
}
