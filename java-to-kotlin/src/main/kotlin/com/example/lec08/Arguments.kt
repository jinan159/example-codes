package com.example.lec08

/*
    1. 가변인자를 사용할 때는 vararg 라는 키워드를 파라미터 앞에 적어야 함
    2. Java 와는 다르게 가변인자에 배열을 넣을 수 없음
        - 그래서 스프레드 연산자 * 를 사용해서 내용물들을 펼쳐줘야함
          (C언어 포인터 사용법이랑 비슷하지만, 포인터 아님)
 */

fun main() {
    printAll("A", "B", "C")

    val array = arrayOf("A", "B", "C")
    printAll(*array) // * 스프레드 연산자(포인터 아님)
}

fun printAll(vararg strings: String) {
    for (str in strings) {
        println(str)
    }
}
