package com.example.lec11

/*
1. 자바와 코틀린의 가시성(접근제한자)은 거의 비슷하지만 일부 차이가 있음
        - Java
            - public : 모든 곳
            - protected : 같은 패키지 또는 하위 클래스
            - package private(default) : 같은 패키지만
            - private 선언된 클래스 내부
        - Kotlin
            - public(default) : 모든 곳
            - protected : '선언된 클래스' 또는 하위 클래스
                - 코틀린 클래스에는 사용 불가능
                - 주의할 점 : Java 가 Kotlin 의 protected 요소를 참조할 경우 Java 기준대로 protected 를 해석함
            - internal : 같은 모듈 내에서만(Java, Gradle 등에서의 모듈)
                - 멀티 모듈 프로젝트 A 모듈의 internal 클래스는 B 모듈에서 접근할 수 없음
                - 주의할 점 : Java 가 Kotlin 의 internal 요소를 참조할 경우 public 으로 간주함
            - private 선언된 클래스 내부
*/

// public 클래스
// 어디서나 접근할 수 있고, 어디서든 생성자를 호출할 수 있음
public class PublicClass public constructor()

// protected 클래스
// 어디서나 접근할 수 있지만, 자기자신 혹은 상속한 클래스에서만 생성자를 호출할 수 있음
open class ProtectedClass protected constructor()

// internal 클래스
// 모듈 내에서만 접근할 수 있고 생성자를 호출할 수 있음
internal class InternalClass internal constructor()

// private 클래스
// 현재 클래스(or 파일)에서만 접근할 수 있고, 클래스 내부에서만 생성자를 호출할 수 있음
// 현재로서는 인스턴스를 생성할 수 없는 클래스임
private class PrivateClass private constructor()