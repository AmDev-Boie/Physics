package com.real.Classes.PrimativeAdditions;

public class JMath {

    // this exists only because im super lazy and want a shorthand way to do these so i dont need to remember them every time ill need it in some niche scenario.

    // plus its a bonus that it will make the interface a little better for development.

    public static double RandomBetween(double lower, double upper) {
        return (Math.random() * (upper - lower) + lower);
    }

    public static Double RoundToDecimal(Double number, int roundingPlace) {
        return (Math.ceil(number * (Math.pow(10, roundingPlace)))/Math.pow(10, roundingPlace));
    }

}