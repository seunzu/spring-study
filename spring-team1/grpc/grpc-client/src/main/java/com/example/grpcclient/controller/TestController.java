package com.example.grpcclient.controller;

import com.example.grpcclient.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;

    @GetMapping("/greet")
    public String greet(@RequestParam String name) {
        return testService.sendGreeting(name);
    }
}
