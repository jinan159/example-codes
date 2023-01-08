package com.example.item23.inheritance.disadvantage;

public class Circle extends AbstractShape {

    public Circle(double radius) {
        super(radius);
    }

    public double area() {
        return Math.PI * (radius * radius);
    }
}
