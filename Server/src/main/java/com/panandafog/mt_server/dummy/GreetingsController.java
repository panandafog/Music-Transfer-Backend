package com.panandafog.mt_server.dummy;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingsController {

    @RequestMapping("/greeting")
    String hello() {
        return "Hello!";
    }
}
