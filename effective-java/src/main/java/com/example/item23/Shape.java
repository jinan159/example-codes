package com.example.item23;

public class Shape {
    private enum ShapeType { TRIANGLE, RECTANGLE }

    private ShapeType shapeType;

    private double width;
    private double height;

    public static Shape createTriangle(double width, double height) {
        Shape triangle = new Shape();
        triangle.shapeType = ShapeType.TRIANGLE;
        triangle.width = width;
        triangle.height = height;

        return triangle;
    }

    public static Shape createRectangle(double width, double height) {
        Shape rectangle = new Shape();
        rectangle.shapeType = ShapeType.RECTANGLE;
        rectangle.width = width;
        rectangle.height = height;

        return rectangle;
    }

    // 넓이 계산
    public double area() {
        switch (this.shapeType) {
            case TRIANGLE: return (width * height) / 2;
            case RECTANGLE: return width * height;
            default:
                throw new IllegalStateException("지원하지 않는 모양입니다.");
        }
    }
}