package com.example.item10;

public class Point {

    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Point)) return false;
        Point p = (Point) o;
        return this.x == p.x && this.y == p.y;
    }

    public static void main(String[] args) {
        ColorPoint p1 = new ColorPoint(1,2, Color.RED);
        Point p2 = new Point(1,2);
        ColorPoint p3 = new ColorPoint(1,2, Color.BLUE);

        System.out.println(p1.equals(p2)); // true (좌표 같음)
        System.out.println(p2.equals(p3)); // true (좌표 같음)
        System.out.println(p1.equals(p3)); // false (좌표 같음, 색상 다름)
    }
}

class ColorPoint extends Point {

    // 추이성 위반 개선
    // private final Point point;
    private final Color color;

    public ColorPoint(int x, int y, Color color) {
        super(x, y);
        this.color = color;
    }

    // 추이성 위반 개선
    // public ColorPoint(int x, int y, Color color) {
    //     this.point = new Point(x, y);
    //     this.color = color;
    // }

    // 추이성 위반 개선
    // public Point asPoint() {
    //     return this.point;
    // }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Point)) return false;

        // 좌표 비교
        if (!(o instanceof ColorPoint)) return o.equals(this);

        // 좌표, 색상 비교
        ColorPoint cp = (ColorPoint) o;
        return super.equals(cp) && this.color == cp.color;
    }
}

class Color {

    public static final Color RED = new Color("red");
    public static final Color BLUE = new Color("blue");
    private final String color;

    public Color(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}

