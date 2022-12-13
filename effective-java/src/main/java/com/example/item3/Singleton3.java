package com.example.item3;

/**
 * 3. 열거 타입 활용 방식
 */
public enum Singleton3 implements Singleton {
    INSTANCE;

    @Override
    public void doSomething() {
        System.out.println("Singleton3.doSomething");
    }
}
