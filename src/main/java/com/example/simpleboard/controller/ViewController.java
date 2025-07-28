package com.example.simpleboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/form")
    public String postForm() {
        return "postForm"; // templates/postForm.html 렌더링
    }
}