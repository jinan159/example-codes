package com.example.lec14

/*
 	1. Enum Class
		- Java 에서의 사용법과 완전 동일함
        - Kotlin 에서는 when expression 에서 핸들링 하지 않은 enum 이 있으면 warning 이 뜸
 */

fun handleCountry(country: Country) {
    return when (country) {
        Country.KOREA -> TODO()
        Country.AMERIKA -> TODO()
    }
}

enum class Country(
	private val code: String,
) {
    KOREA("KO"),
    AMERIKA("US")
}