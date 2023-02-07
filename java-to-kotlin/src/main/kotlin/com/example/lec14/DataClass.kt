package com.example.lec14

/*
 	1. Data Class
    	- equals, hashCode, toString 이 자동으로 생성됨
        - toString 은 순환참조 문제가 생길 수 있으니 주의해야하지 않을까?
        	- 아래 번외에서 다뤘음
 */

data class Person (
    val name: String,
    val age: Int
)

// 번외로, 다음과 같은 경우 순환 참조 문제가 발생하여 StackOverflowError 가 발생할 수 있음
// Data Class 의 toString 은 주의해서 사용하거나, 필요하다면 override 해서 사용해야할 것 같음
fun circularReference() {
	val a = A(null)
    val b = B(a)
    a.b = b
    
    println(a.toString())
}
data class A(var b: B?)
data class B(var a: A)