package com.example.item3;

/**
 * 4. LazyHolder 방식
 */
public class Singleton4 implements Singleton {

    private Singleton4() { }

    private static class LazyHolder {
        private static final Singleton4 INSTANCE = new Singleton4();
    }

    public static Singleton4 getInstance() {
        return LazyHolder.INSTANCE;
    }

    @Override
    public void doSomething() {
        System.out.println("Singleton3.doSomething");
    }
}
