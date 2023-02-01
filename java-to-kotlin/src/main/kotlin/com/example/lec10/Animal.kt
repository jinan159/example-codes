package com.example.lec10

/*
    1. val 필드는 final 이기 때문에 getter 도 기본적으로 final 임
        - 이 val 필드의 getter 를 하위 클래스에서 오버라이드 하려면 open 키워드를 붙여야 함
 */

abstract class Animal(
    protected val species: String,
    protected open val legCount: Int // open 키워드를 붙여서 override 허용
) {
    abstract fun move()
}