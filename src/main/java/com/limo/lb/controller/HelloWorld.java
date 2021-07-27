package com.limo.lb.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {
    private String property;
    @RequestMapping("/hello")
    public String hello(){
        return "hello world!";
    }
}
