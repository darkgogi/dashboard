package com.example.hello.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class IndexController {

    @Value("${welcome.message:Hello World}")
    private String message = "";

    private final AtomicInteger counter = new AtomicInteger();

    @GetMapping(path="/")
    public String hello() {
        return message + " " + counter.incrementAndGet();
    }
}
