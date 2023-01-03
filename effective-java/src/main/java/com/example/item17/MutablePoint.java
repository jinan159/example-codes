package com.example.item17;

/**
 * 1.2.2 멀티 스레드 환경에서도 동기화할 필요가 없음<br>
 * <a href="https://github.com/Study-2-Effective-Java/Effective-Java/discussions/37">참고 링크</a>
 */
public class MutablePoint {
    private int x;
    private int y;

    public MutablePoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
