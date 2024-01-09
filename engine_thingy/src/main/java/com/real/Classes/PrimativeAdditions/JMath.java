package com.real.Classes.PrimativeAdditions;

public class JMath {

    public static double RandomBetween(double lower, double upper) {
        return (Math.random() * (upper - lower) + lower);
    }

}