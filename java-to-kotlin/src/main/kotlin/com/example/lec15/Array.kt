package com.example.lec15

fun main() {
    // 배열을 만들때는 이렇게 arrayOf 를 선언하면 된다.
    val array = arrayOf(100, 200, 300, 400, 500)

    // 이렇게 그냥 iterate 할 수도 있다.
    for (i in array) {
        println(i)
    }

    // indices 는 배열 크기만큼의 IntRange 클래스를 반환한다.
    // 이는 배열의 index 로 활용할 수 있다.
    for (i in array.indices) {
        println(i)
    }

    // withIndex 를 통해 index 와 value 를 동시에 가져올 수 있다.
    for ((i, v) in array.withIndex()) {
        println("index: $i, value: $v")
    }
}