package com.example.lec10

/*
출력 결과는 다음과 같음
    init 에서 호출하는 num 은 getter 인데, 이 getter 는 Derived 에서 override 되었음
    그런데 Derived 의 num getter 에서 반환하는 field 는 아직 초기화되지 않아 Int 기본값 0을 반환
    그래서 Base 의 this.num 에서는 반환받은 0을 출력함

    중요한것 : 클래스의 init 블록에서는 open 인 property 에는 절대 접근하면 안됨(값 보장이 안됨)

Base init
Derived getter
0
Derived init
Derived getter
300
*/

open class Base(
    open val num: Int = 100
) {
    init {
        println("Base init")
        println(this.num) // Derived.getNum()
    }
}

class Derived(
    num: Int
) : Base(num) {
    override val num: Int = num
        get() {
            println("Derived getter")
            return field
        }
    init {
        println("Derived init")
        println(this.num)
    }
}

fun main() {
    Derived(300)
}
