package com.example.lec08

fun main() {
    // default
    repeat("good") // good 3줄 출력
    repeat("good", 2, false) // goodgood 출력
    repeat("good", newLine = true) // good 2줄 출력
}

fun repeat(
    str: String,
    num: Int = 3,
    newLine: Boolean = true
) {
    for (i in 1..num) {
        if (newLine) {
            println(str)
        } else {
            print(str)
        }
    }
}
