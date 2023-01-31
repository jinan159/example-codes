package com.example.lec02

fun main() {
    var str: String? = "ABC"
    // println(str.length) // compile error(unsafe call)
    println(str?.length) // safe call

    str = null;
    print(str?.length ?: 0) // Elvis operator

    str = "ABCDEFG"
    print(str!!.length) // 컴파일러에게 이 변수는 null 이 아님을 알림
}