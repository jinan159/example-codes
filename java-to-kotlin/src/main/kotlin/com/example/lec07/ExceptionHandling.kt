package com.example.lec07

import java.lang.IllegalArgumentException
import java.lang.NumberFormatException

/*
    1. try-catch-finally 도 Expression 으로 간주된다
 */

fun tryCatchFinally1(str: String): Int {
    try {
        return str.toInt()
    } catch (e: NumberFormatException) {
        throw IllegalArgumentException("주어진 ${str}는 숫자가 아닙니다", e)
    }
}

// try-catch 도 expression 으로 간주된다
fun tryCatchFinally2(str: String): Int? {
    return try {
        str.toInt()
    } catch (e: NumberFormatException) {
        null
    }
}