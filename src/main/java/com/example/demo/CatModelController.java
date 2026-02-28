package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// TODO реализовать html отображение
@Controller
@RequestMapping("/")
public class CatModelController {
    @RequestMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("user", new User("John", 30));
        return "hello";
    }
    @GetMapping
    public String login() {

    }
}
