package com.example.lec11

/*
    1. 클래스 상속, 함수와 필드 오버라이드 하기 위해서는 open 키워드를 붙여야 함
        - 추상클래스, 인터페이스는 open 안붙여도 상속 가능
            - 추상클래스의 필드는 open 붙여야 함
            - 추상클래스, 인터페이스의 함수는 안붙여도 됨
 */

open class Base {
    open val num: Int = 10
    open fun test() {}
}

class Sub : Base() {
    override val num: Int = 11
    override fun test() { }
}

// --------------------------------

// abstract 를 붙이면 open 을 생략해도 됨
abstract class AbstractBase {
    abstract val num: Int
    val str: String = ""

    abstract fun hello() // abstract 는 body 를 가질 수 없음
    open fun world() { } // 일반 함수는 오버라이드를 허용하려면 open 을 붙여야 함
}

class Sub2 : AbstractBase() {
    override val num: Int
        get() = 1

    override fun hello() { }
    override fun world() {}
}

// --------------------------------

// protected 는 클래스 앞에는 못붙임
// 생성자 앞에 붙여야하고, 이때는 constructor 키워드를 붙여줘야함
open class ProtectedConstructorClass protected constructor()
class Sub3 : ProtectedConstructorClass()
