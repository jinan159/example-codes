package com.example.lec10

interface Flyable {

    // 아래는 필드처럼 보이지만 사실 getter 혹은 setter 선언문임
    // val flyAbility: Int // backing field 가 없는 getter 임
    // var flyAbility: Int // backing field 없는 getter, setter

    // interface 에서 default 키워드 없어 body 를 선언할 수 있음
    fun act() {
        println("파닥 파닥")
    }
}