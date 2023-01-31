package com.example.lec01

import javax.print.attribute.IntegerSyntax

/*
    1. primitive type 과 wrapper type 가 상황에 따라 자동으로 바뀐다.
        - 선언문이 `var number: Int` 로 wrapper type 처럼 보이긴 하지만
          nullable 이 아닌 경우 java code 로 decompile 하면 primitive type 이다.
    2. 변수 선언시 가변(var), 불면(val) 여부를 반드시 선언해야 한다.
    3. 모든 변수는 기본적으로 null 이 불가하고 nullable 변수를 위해서는 추가해줘야 한다.
        - var str: String = "not null"
        - var str: String? = "nullable"
    4. 인스턴스 생성시에는 new 를 붙이지 않는다.
 */

fun main() {
    /* JAVA
        long number = 10L
        Long number = 10L
     */

    var number: Long = 10L
    println("== primitive type and wrapper type ====================")
    println("number = $number")

    /* JAVA
        final long number2 = 10L
        final Long number2 = 10L
     */
    val number2: Long = 10L
    println("== immutable variable ====================")
    println("number2 = $number2")

    /* JAVA
        Long number3 = null
     */
    var number3: Long? = null
    println("== nullable variable ====================")
    println("number3 = $number3")

    /* JAVA
        Person person1 = new Person("김진완");
        final Person person2 = new Person("김진완");
     */
    println("== instance ====================")
    var person = Person("김진완 1")
    val person2 = Person("김진완 2")
    println("person = $person")
    println("person2 = $person2")
}

private class Person(val name: String) {

    override fun toString(): String {
        return "Person name : $name";
    }
}