package com.example.courseproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExampleController {
    @GetMapping("/example")
    public String getExamplePage(){
        return "example_page";
    }
}
