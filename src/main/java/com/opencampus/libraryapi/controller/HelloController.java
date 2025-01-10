package com.opencampus.libraryapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * The hello controller
 */
@RestController
@RequestMapping("/hello") // The base root [/api/v1/] + [RESSOURCE NAME]
public class HelloController {
    @GetMapping // HTTP GET + HELLO => Is uniq in my api
    Map<String, String> sayHello(){
        return Map.of("welcomeMessage", "Hello Soumaila !");
    }

    @GetMapping("/test") // HTTP GET + HELLO + /test => Is uniq in my api
    Map<String, String> sayHello2(){
       /*
        {
            "welcomeMessage": "Hello votre nom",
            "myAge": "20"
        }
       */
        return Map.of("welcomeMessage", "Hello soumaila", "myAge", "20");
    }
}
