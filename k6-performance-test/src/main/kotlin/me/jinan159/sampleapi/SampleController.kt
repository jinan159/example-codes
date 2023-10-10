package me.jinan159.sampleapi

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/hello")
class SampleController {
    @GetMapping
    fun world(): Map<String, String> {
        return mapOf(
            "hello" to "world"
        )
    }
}