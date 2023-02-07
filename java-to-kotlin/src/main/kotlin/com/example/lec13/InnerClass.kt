package com.example.lec13

/*
 	0. 권장되는 내부 클래스, 권장되지 않는 내부 클래스가 있음
    	- 권장되는 내부 클래스 : 외부 클래스를 참조하지 않음, 독립적, GC 유리
        - 권장되지 않는 클래스 : 외부 클래스를 참조함, GC 불리
	1. Kotlin 에서 권장되는 내부 클래스를 작성하려면 class 를 사용함
   	2. Kotlin 에서 권장되지 않는 내부 클래스를 작성하려면 inner class 를 사용함
 */

fun main() {
    val child1 = Parent.Child1() // static 처럼 접근하여 생성
    val child2 = Parent().Child2() // 바깥 클래스를 인스턴스화 해야 접근 가능
    
    println(child1.num) // 5
    println(child2.num) // 10
}

class Parent {
    val num = 10
    
    // Java 에서는
    // public static class Child1 { ... }
    class Child1 {
        val num = 5
    }
    
    // Java 에서는
    // public static class Child2 { ... }
    inner class Child2 {
        val num : Int
        	// Java 에서는
        	// Parent.this.num
        	get() = this@Parent.num
    }
}