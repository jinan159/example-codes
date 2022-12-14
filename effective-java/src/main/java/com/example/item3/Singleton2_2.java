package com.example.item3;

/**
 * 2. 정적 팩터리 방식
 *     2) Lazy Initialization, 지연 초기화
 */
public class Singleton2_2 implements Singleton {
    private static Singleton2_2 INSTANCE;
    private Singleton2_2() { }

    public static Singleton2_2 getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Singleton2_2();
        }

        return INSTANCE;
    }

    @Override
    public void doSomething() {
        System.out.println("Singleton2_2.doSomething");
    }
}
