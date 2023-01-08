package com.example.item23.inheritance;

public abstract class AbstractShape {

    protected final double width;
    protected final double height;

    public AbstractShape(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public abstract double area();
}
