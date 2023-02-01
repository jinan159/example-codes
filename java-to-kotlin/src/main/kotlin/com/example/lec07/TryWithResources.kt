package com.example.lec07

import java.io.BufferedReader
import java.io.Closeable
import java.io.File
import java.io.FileReader

/*
    1. Kotlin 은 try-with-resources 구문이 없음
        - 대신에 Closeable 을 구현하면 use 구문으로 같은 역할을 함
 */

fun main() {
    val currentFile = File(".")
    val file = File(currentFile.absolutePath + "/README.md")
    BufferedReader(FileReader(file)).use {
        println(it.readLine())
    }

    // Closeable 을 구현하면 use 구문을 사용할 수 있음
    IOClass().use {
        // something...
    }
}

private class IOClass : Closeable {
    override fun close() {
        TODO("Not yet implemented")
    }
}