package com.example.lec07

import java.io.BufferedReader
import java.io.File
import java.io.FileReader

/*
    1. Kotlin 에서는 예외를 모두 Unchecked Exception 로 간주함
 */

fun main() {
    val currentFile = File(".")
    val file = File(currentFile.absolutePath + "/README.md")
    val reader = BufferedReader(FileReader(file))
    println(reader.readLine())
    reader.close()
}