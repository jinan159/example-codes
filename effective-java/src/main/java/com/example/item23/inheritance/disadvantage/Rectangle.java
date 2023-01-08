package com.example.item23.inheritance.disadvantage;

public class Rectangle extends AbstractShape {

    public Rectangle(double width, double height) {
        super(width, height);
    }

    @Override
    public double area() {
        return width * height;
    }
}
