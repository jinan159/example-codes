package com.example.lec09

/*
    1. Kotlin 클래스의 기본 생성자는 클래스명 옆에 써줌
        - 초기화 로직은 init 블록을 활용함
    2. 추가 생성자는 constructor 키워드를 붙여 본문에 써줌
        - 하지만 Kotlin 에서는 default parameter 를 활용하는것을 더 권장함
    3. Getter, Setter 를 자동으로 만들어줌
        - val : getter
        - var : getter, setter
        - 이것들도 custom getter, custom setter 를 만들어 사용할 수 있음
*/

fun main() {
    val person = Person("김진완", 0)
    // person.name = "" // name 은 val 로 선언되어 setter 가 없어서 값을 변경할 수 없음
    println(person.name) // person.getName()

    person.age = 29 // person.setAge(29)
    println(person.age) // person.getAge()
}

/*
class Person constructor(name: String, age: Int) {
    val name = name
    var age = age
}
*/

/*
class Person (name: String, age: Int) {
    val name = name
    var age = age
}
*/

// ----------------------------------------------------------------------------

/*
// 여기처럼 생성자를 여러개 만들수도 있음
class Person(val name: String, var age: Int) {
    init {
        if (age < 0) {
            throw IllegalArgumentException("나이는 ${age}일 수 없습니다")
        }
    }

    constructor(name: String) : this(name, 1)

    constructor() : this("홍길동")
}
*/

// 하지만 Kotlin 은 여기 생성자처럼 default parameter 를 활용하는것을 권장함
class Person(
    val name: String = "홍길동",
    var age: Int = 1
) {
    init {
        if (age < 0) {
            throw IllegalArgumentException("나이는 ${age}일 수 없습니다")
        }
    }

    /*
    // 함수처럼 만드는 getter
    fun isAdult(): Boolean {
        return this.age >= 20
    }
    */

    // property 처럼 만드는 custom getter
    val isAdult: Boolean
        get() = this.age >= 20
}

