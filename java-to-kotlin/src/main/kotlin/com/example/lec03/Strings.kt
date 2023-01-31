package com.example.lec03

/*
    1. 문자열 에 변수 삽입 가능
        ${변수}
    2. 여러줄 문자열 손쉽게 작성 가능
    3. 문자열에서 특정 문자 가져올때 배열처럼 접근 가능
 */
fun main() {
    // 문자열에 변수 삽입 가능
    val name = "김진완"
    println("이름 : ${name}")

    // 여러줄 문자열 입력
    val str = """
        안녕하세요.
        제 이름은 ${name} 입니다.
    """.trimIndent()
    println(str)

    // 문자열의 특정 인덱스에 접근할때 배열처럼 가능
    val alphabet = "ABCDEFG"
    println(alphabet[0])
}