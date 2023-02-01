package com.example.lec10

interface Swimable {

    // interface 에서 default 키워드 없어 body 를 선언할 수 있음
    fun act() {
        println("어푸 어푸")
    }
}