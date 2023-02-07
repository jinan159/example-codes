package com.example.lec12

/*
	1. Kotlin 에서는 익명 객체를 만들때도 object 라는 키워드를 사용함
 */

fun main() {
	moveSomething(object: Moveable {
        override fun move() {
            println("move move")
        }
    })
}

fun moveSomething(moveable: Moveable) {
    moveable.move()
}

interface Moveable {
	fun move()
}