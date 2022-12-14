package com.example.item3;

/**
 * 2. 정적 팩터리 방식
 *     1) static final 필드 활용
 */
public class Singleton2_1 implements Singleton {
    private static final Singleton2_1 INSTANCE = new Singleton2_1();
    private Singleton2_1() { }

    public static Singleton2_1 getInstance() {
        return INSTANCE;
    }

    @Override
    public void doSomething() {
        System.out.println("Singleton2_1.doSomething");
    }
}
