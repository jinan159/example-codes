package com.example.restdocsswagger.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public SampleResponse returnNameAndAge(@RequestBody SampleRequest request) {
        return new SampleResponse(request.name(), request.age());
    }
}
