package com.real.Classes.Types;

import java.util.Vector;

public class ObjectClass {
    // class stuff

    // public static Object[][] ObjectList = new Object[100][2];

    public static Vector<Object[]> ObjectList = new Vector<Object[]>();

    public static int RegisterObject(String Name, Object Obj) {
        Object[] Element = new Object[] {Name, Obj};

        // ObjectList[ID][1] = Name;
        ObjectList.add(Element);

        return ObjectList.indexOf(Element);
    };

    // object stuff
    
    protected String Name;
    protected int ID;
    protected boolean anchored;
    protected boolean canCollide;

    public ObjectClass(String Name) {
        this.Name = Name;
        this.ID = RegisterObject(Name, this);
        this.anchored = false; // TODO make a physics entity class.
        this.canCollide = true;
    };

    // getter methods

    public String GetName() {
        return this.Name;
    };

    public int GetID() {
        return this.ID;
    };

    public boolean GetAnchored() {
        return this.anchored;
    };

    public boolean GetCanCollide() {
        return this.canCollide;
    };

    // setter methods

    public void SetName(String Value) {
        this.Name = Value;
    };

    public void SetID(int Value) {
        this.ID = Value;
    };

    public void SetAnchored(boolean Value) {
        this.anchored = Value;
    };

    public void SetCanCollide(boolean Value) {
        this.canCollide = Value;
    };

    // Override methods

    @Override

    public String toString() {
        return this.ID + "";
    };
}

