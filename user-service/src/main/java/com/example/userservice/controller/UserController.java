package com.example.userservice.controller;

import com.example.userservice.vo.Greeting;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class UserController {

    // 1)
    // private final Environment env;

    // 2)
    private final Greeting greeting;

    @GetMapping("/health_check")
    public String status() {
        return "It's working in user service.";
    }

    @GetMapping("/welcome")
    public String welcome() {
        // 1)
        // return env.getProperty("greeting.message");

        // 2)
        return greeting.getMessage();
    }
}
