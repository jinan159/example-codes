package com.example.lec12

/*
	1. Kotlin 에서는 static 이 없음
    	- 그래서 companion object 를 만들어서 static 처럼 사용(공유)함
        - companion object 는 클래스이기 때문에 클래스 이름 지정, interface 구현, 상속등을 할 수 있음
    2. 상수에는 const 키워드를 사용하자
    	- val, var 는 런타임에 실제로 변수에 값이 할당됨
        - 하지만 const 를 붙이면 컴파일 타임에 변수에 값이 할당됨
        - 기본 타입과 String 에만 붙일 수 있음
    3. Java 에서 Kotlin 클래스의 companion object 를 사용할 수 있음(java 에서는 static 으로 인식함)
    	- Kotlin 에서는 반드시 @JvmStatic 을 붙여줘야함
*/

class Person private constructor (
	var name: String,
    var age: Int
) {
    companion object : Log {
        private const val MIN_AGE = 1
        fun newBaby(name: String): Person {
            return Person(name, MIN_AGE)
        }
        override fun log() {
            println("companion object logging")
        }
    }
}

interface Log {
    fun log()
}