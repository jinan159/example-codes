package com.example.lec10

class Penguin(
    species: String,
// Java 와는 다르게 extends, implements 구분없이 같이 작성한다
) : Animal(species, 2), Swimable, Flyable {

    private val wingCount: Int = 2

    override fun move() {
        println("펭귄이 움직인다.")
    }

    override val legCount: Int
        get() {
            return super.legCount + wingCount
        }

    override fun act() {
        super<Flyable>.act()
        super<Swimable>.act()
    }
}