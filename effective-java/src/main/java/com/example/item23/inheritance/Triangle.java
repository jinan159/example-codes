package com.example.item23.inheritance;

public class Triangle extends AbstractShape {

    public Triangle(double width, double height) {
        super(width, height);
    }

    @Override
    public double area() {
        return (width * height) / 2;
    }
}
