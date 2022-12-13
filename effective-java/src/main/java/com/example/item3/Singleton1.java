package com.example.item3;

/**
 * 1. public static final 필드 방식
 */
public class Singleton1 implements Singleton {
    public static final Singleton1 instance = new Singleton1();
    private Singleton1() { }

    @Override
    public void doSomething() {
        System.out.println("Singleton1.doSomething");
    }
}
