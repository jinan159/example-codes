package me.jinan159.springsecurityoauth2

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/operator/authentication/google")
class OAuthController {
    @GetMapping
    fun auth(
        @RequestParam code: String,
        @RequestParam state: String
    ) {
        println(code)
        println(state)
    }
}