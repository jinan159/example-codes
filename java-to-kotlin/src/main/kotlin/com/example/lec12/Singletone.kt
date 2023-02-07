package com.example.lec12

/*
	1. Kotlin 에서는 싱글톤을 만들고싶으면 object 키워드를 붙이면 됨 
    	- 싱글톤은 따로 인스턴스는 생성하지 않고, static 처럼 호출하여 사용하면 됨
 */
object Singletone {
    val num = 123
}
