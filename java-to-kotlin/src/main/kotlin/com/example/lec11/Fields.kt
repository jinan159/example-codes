package com.example.lec11

class Car {
    // internal 수준의 가시성을 가진 name 의 getter 제공
    internal val name: String = "name"

    // private 수준의 가시성을 가진 owner 의 getter, setter 제공
    private var owner: String = "김진완"

    // public 수준의 가시성을 가진 num 의 getter 과
    // private 수준의 가시성을 가진 num 의 setter 제공
    var num: Int = 1
        private set
}