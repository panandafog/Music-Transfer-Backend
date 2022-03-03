package com.panandafog.mt_server.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingsController {

    @RequestMapping("/greeting")
    String hello() {
        return "Hello!";
    }
}
