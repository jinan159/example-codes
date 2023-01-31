package com.example.lec03

import com.example.lec02.Person

/*
    1. 타입은 초기값을 보고 추론된다.
    2. Java 와 다르게 명시적으로 형변환(캐스팅) 해야한다
        - is, !is : 타입 확인
        - as, as? : 타입 변환
    3. Any, Unit, Nothing 타입 존재
        - Any : Java 의 Object 처럼 최상위 타입(Any? 는 nullable 포함한 모든것의 최상위)
        - Unit : Java 의 Void 와 동일
        - Nothing : 정상적으로 끝나지 않은 함수의 반환(무조건 예외 발생 or 무한 루프)
 */
fun main() {
    // 타입 추론
    val intNum = 1 // Int
    val longNum = 2L // Long

    // 명시적 형변환
    val longNum2: Long = intNum.toLong()

    // 인스턴스 형변
    val obj: Any = Person("김진완")
    /*
    if (obj is Person) {
        val person2 = obj as Person
    }
    */
    val person2 = obj as? Person
    println(person2?.toString())
}