package com.example.lec05

import java.util.StringJoiner

/*
    제어문(if, when(switch) 등)
    1. if, when(switch) 는 Java 에서는 Statement 이지만, Kotlin 에서는 Expression 임
        - Statement : 프로그램의 문장, 하나의 값으로 도출되지 않음
        - Expression : 하나의 값으로 도출되는 문장
*/

// 일반적인 상황에서는 Java 와 동일하게 사용할 수 있음
fun validateScoreIsNotNegative(score: Int) {
    if (score !in 1..100) {
        throw IllegalArgumentException()
    }
}

// if 는 Expression 이기 때문에 주석의 if 문을 아래와 같이 변경할 수 있음
// 그래서 if-else 가 삼항연산자를 대신하기때문에 Java 에서의 삼항연산자는 없음
fun getPassOrFail(score: Int): String {
    /*
    if (score >= 50) {
        return "P"
    } else {
        return "F"
    }
    */
    return if (score >= 50) {
        "P"
    } else {
        "F"
    }
}

// return if expression
fun getGrade(score: Int): String {
    return if (score>= 90) {
        "A"
    } else if (score>= 80) {
        "B"
    } else if (score>= 70) {
        "C"
    } else {
        "D"
    }
}

fun getGradeWithSwitch(score: Int): String {
    return when (score) {
        in 90..99 -> "A"
        in 80..89 -> "B"
        in 70..79 -> "C"
        else -> "D"
    }
}

fun startWithA(obj: Any): Boolean {
    return when(obj) {
        is String -> obj.startsWith("A")
        else -> false
    }
}

fun judgeNumber(number: Int) {
    when (number) {
        1, 0, -1 -> println("많이 본  값 입니다")
        else -> println("본적 없는 값 입니다")
    }
}

fun judgeNumber2(number: Int) {
    when {
        number == 0 -> println("주어진 숫자는 0 입니다")
        number % 2 == 0 -> println("주어진 숫자는 짝수 입니다")
        else -> println("주어진 숫자는 홀수 입니다")
    }
}