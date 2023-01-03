package com.example.item17;

/**
 * 1.2.2 멀티 스레드 환경에서도 동기화할 필요가 없음<br>
 * <a href="https://github.com/Study-2-Effective-Java/Effective-Java/discussions/37">참고 링크</a>
 */
public class ImmutablePoint {
    private final int x;
    private final int y;

    public ImmutablePoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public ImmutablePoint setX(int x) {
        return new ImmutablePoint(x, this.y);
    }

    public int getY() {
        return y;
    }

    public ImmutablePoint setY(int y) {
        return new ImmutablePoint(this.x, y);
    }
}
