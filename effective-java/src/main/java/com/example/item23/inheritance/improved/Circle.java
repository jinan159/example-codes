package com.example.item23.inheritance.improved;

public class Circle implements AbstractShape {

    private final double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double area() {
        return Math.PI * (radius * radius);
    }
}
