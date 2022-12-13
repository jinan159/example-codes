package com.example.item3;

/**
 * 2. 정적 팩터리 방식
 *     3) Lazy Initialization + Synchronization, 지연 초기화 + 동기화
 */
public class Singleton2_3 implements Singleton {
    private static Singleton2_3 instance;
    private Singleton2_3() { }

    public static synchronized Singleton2_3 getInstance() {
        if (instance == null) {
            instance = new Singleton2_3();
        }

        return instance;
    }

    @Override
    public void doSomething() {
        System.out.println("Singleton2_3.doSomething");
    }
}
