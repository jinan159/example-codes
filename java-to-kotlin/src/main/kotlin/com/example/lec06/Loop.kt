package com.example.lec06

/*
    1. foreach
        - ':' 대신 in 을 사용
        - Java 와 동일하게 Iterable 구현체라면 사용할 수 있음
        - a..b 는 IntRange 라는 등차 수열을 만드는 구현체임
            - 이는 IntProgress 를 상속함
            - 여기서 사용되는 downTo, step 은 모두 함수임
                - downTo 는 Int 의 함수로 IntProgress 를 반환
                - step 은 IntRange 의 함수로 step 이 변경된 새로운 IntRange 를 반환
 */
fun foreachLoop() {
    val numbers = listOf(1L, 2L, 3L)

    for (number in numbers) {
        println(number)
    }


    // 1씩 올라감
    val range = IntRange(1,3) // 이거랑 for 문의 in 다음의 a..b 랑 같음
    for (i in 1..3) {
        println(i)
    }

    // 1씩 내려감
    /*
    val num = 3
    val range2 = num.downTo(1)
    */
    val range2 = 3.downTo(1)
    for (i in 3 downTo 1) {
        println(i)
    }

    // 2씩 올라감
    val range3 = IntRange(1, 6).step(2)
    for (i in 1..6 step 2) {
        println(i)
    }

    // 2씩 내려감
    val range4 = 3.downTo(1).step(2)
    for (i in 6 downTo 1 step 2) {
        println(i)
    }
}

// while, do-while 은 완전히 동일하다
fun whileLoop() {
    val i = 0
    while (i < 10) {

    }

    val j = 0
    do {

    } while (j < 10)
}


