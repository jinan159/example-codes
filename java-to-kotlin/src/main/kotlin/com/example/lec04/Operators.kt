package com.example.lec04

import java.time.Month

/*
    1. 단항 연산자, 산술 연산자, 산술 대입 연산자 모두 Java 와 동일
        - 단항 연산자 : ++ --
        - 산술 연산자 : + - * / %
        - 산술 대입 연산자 : += -= *= /= %=
    2. 비교 연산자, 동등성, 동일성(< > >= <=)
        - 인스턴스 비교시 비교연산자 사용하면 compareTo 가 자동으로 호출된다
    3. 범위 연산자
        - 범위 포함 여부 연산자 : in !in
        - 범위 생성 연산자 : a..b
    4. 연산자 오버로딩
 */

fun unaryOperator() {
    var num = 1
    num++
    println("num + 1 = ${++num}")

    num--
    print("num - 1 = ${--num}")
}

// 인스턴스 비교 연산자
fun compareOperatorWithInstance() {
    val money1 = Money(1);
    val money2 = Money(2);

    // Comparable 구현체인 경우 비교 연산자를 사용할 수 있음
    println(money1 < money2)
}

// 동등성, 동일성 비교
fun identifyAndEquality() {
    val money1 = Money(1);
    val money2 = Money(1);
    val money3 = money2

    println(money1 == money2) // 동등성 비교, true
    println(money1 === money2) // 동일성 비교, false
    println(money2 === money3) // 동일성 비교, true
}

// 연산자 오버로딩
fun operatorOverloading() {
    val money1 = Money(1);
    val money2 = Money(1);

    println(money1 + money2) // amount : 2
    println(money1 - money2) // amount : 0
}

fun main() {
    operatorOverloading()
}

private class Money: Comparable<Money> {

    private val amount: Int

    constructor(amount: Int) {
        this.amount = amount
    }

    // return 구문에서 if else-if else 사용 가능
    override fun compareTo(other: Money): Int {
        return if (this.amount > other.amount) {
            1
        } else if (this.amount < other.amount) {
            -1
        } else {
            0
        }
    }

    // + 연산자 오버로딩
    operator fun plus(other: Money): Money {
        return Money(this.amount + other.amount)
    }

    // - 연산자 오버로딩
    operator fun minus(other: Money): Money {
        return Money(this.amount - other.amount)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Money) return false

        if (amount != other.amount) return false

        return true
    }

    override fun hashCode(): Int {
        return amount
    }

    override fun toString(): String {
        return "amount : ${amount}"
    }
}