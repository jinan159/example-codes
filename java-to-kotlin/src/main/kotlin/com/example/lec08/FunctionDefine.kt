package com.example.lec08

/*
    1. 함수, if Expression 의 {} 을 생략할 수 있음
        - 아래 예제들은 모두 같은 기능을 함
 */

// 전통적인 if 의 형태
fun max1(a: Int, b: Int): Int {
    if (a > b) {
        return a
    } else {
        return b
    }
}

// if 문은 expression 이기 때문에 return 문에 사용 가능함
fun max2(a: Int, b: Int): Int {
    return if (a > b) {
        a
    } else {
        b
    }
}

// = 을 사용하여 함수의 {} 를 생략할 수 있음
// = 을 사용하면 반환 타입을 생략할 수 있음
fun max3(a: Int, b: Int) =
    if (a > b) {
        a
    } else {
        b
    }

// if expression 도 {} 를 생략할 수 있음
fun max4(a: Int, b: Int) = if (a > b) a else b

