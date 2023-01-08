package com.example.item23.inheritance.disadvantage;

public abstract class AbstractShape {

    protected double width;
    protected double height;
    protected double radius;

    public AbstractShape(double radius) {
        this.radius = radius;
    }

    public AbstractShape(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public abstract double area();
}
