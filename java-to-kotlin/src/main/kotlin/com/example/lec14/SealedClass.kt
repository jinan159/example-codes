package com.example.lec14

/*
 	1. Sealed Class, Sealed Interface
		- 외부에서 이 클래스를 상속받지 못하도록 하기 위해 사용
        - 컴파일 타임에 하위 클래스 타입들이 확정되고, 런타임에는 하위 클래스가 추가될 수 없음
        	- 그래서 when expression 에서 enum 처럼 장점을 발휘함
        - 하위 클래스는 같은 패키지에 있어야 함
 */
fun handleSealedClass(car: Car) {
    return when(car) { // enum 과 마찬가지로 빠진 항목이 있으면 warning 발생함
        is Avante -> TODO()
        is Sonata -> TODO()
    }
}

sealed class Car(open val name: String)
class Avante(override val name: String): Car(name)
class Sonata(override val name: String): Car(name)