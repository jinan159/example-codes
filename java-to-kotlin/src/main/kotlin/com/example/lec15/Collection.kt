package com.example.lec15

fun main() {
    list()
    set()
    map()
}

fun collectionNullable() {
    // List<Int?> : null 이 가능한 int 타입이 들어갈 수 있는 List
    // List<Int>? : null 이 가능한 List
    // List<Int?>? : null 이 가능하고, 그 List 안에는 null 이 가능한 int 타입이 들어갈 수 있는 List
}

fun list() {
    /*
        Kotlin 의 Collection 은 mutable 과 immutable 로 나뉜다.
        - Iterable vs MutableIterable
        - Collection vs MutableCollection

        - Mutable
            - MutableList
            - MutableSet
            - MutableMap
        - Immutable
            - List
            - Set
            - Map

        - List : 불변으로 먼저 만들고, 필요하다면 가변으로 만들것
     */

    val numbers = listOf(1, 2, 3, 4, 5)
    val mutableNumbers = mutableListOf(1, 2, 3, 4, 5)

    println(numbers[0])

    for (num in numbers) {
        println(num)
    }

    for ((i, v) in numbers.withIndex()) {
        println("index: $i, value: $v")
    }
}

fun set() {
    val numbers = setOf(1, 2, 3, 4, 5)
    val mutableNumbers = mutableSetOf(1, 2, 3, 4, 5)

    println(numbers.contains(0))

    for (num in numbers) {
        println(num)
    }

    for ((i, v) in numbers.withIndex()) {
        println("index: $i, value: $v")
    }
}

fun map() {
    val numbers = mapOf(1 to "one", 2 to "two", 3 to "three")
    val mutableNumbers = mutableMapOf(1 to "one", 2 to "two", 3 to "three")

    println(numbers[1])

    for (key in numbers.keys) {
        println(key)
        println(numbers[key])
    }

    for ((i, v) in numbers.entries) {
        println("key: $i, value: $v")
    }
}