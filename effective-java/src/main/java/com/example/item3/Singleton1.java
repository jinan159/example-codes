package com.example.item3;

/**
 * 1. public static final íë ë°Šė
 */
public class Singleton1 implements Singleton {
    public static final Singleton1 INSTANCE = new Singleton1();
    private Singleton1() { }

    @Override
    public void doSomething() {
        System.out.println("Singleton1.doSomething");
    }
}
