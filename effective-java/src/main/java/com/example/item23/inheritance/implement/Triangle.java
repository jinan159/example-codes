package com.example.item23.inheritance.implement;

public class Triangle extends AbstractShape {

    private final double width;
    private final double height;

    public Triangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public double area() {
        return (width * height) / 2;
    }
}
