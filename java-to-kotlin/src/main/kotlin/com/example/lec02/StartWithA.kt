package com.example.lec02

/*
public boolean startWithA1(String str) {
    if (str == null) {
        throw new IllegalArgumentException("null 이 들어왔음");
    }
    return str.startWith("A");
}
*/

fun startWithA1(str: String?): Boolean {
    return str?.startsWith("A")
        ?: throw IllegalArgumentException("null 이 들어왔음")
}

/*
public Boolean startWithA2(String str) {
    if (str == null) {
        return null;
    }
    return str.startWith("A");
}
*/

fun startWithA2(str: String?): Boolean? {
    return str?.startsWith("A")
}

/*
public boolean startWithA3(String str) {
    if (str == null) {
        return false;
    }
    return str.startWith("A");
}
 */

fun startWithA3(str: String?): Boolean {
    return str?.startsWith("A") ?: false
}

// 하지만 매개변수 자체에서 null 이 들어올 수 없게 한다면
// 코드도 깔끔하고 안전하다
fun startWithA(str: String): Boolean {
    return str.startsWith("A");
}
