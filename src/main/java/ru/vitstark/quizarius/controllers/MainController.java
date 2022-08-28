package ru.vitstark.quizarius.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("")
    public String toMainPageRedirect() {
        return "redirect:main";
    }

    @GetMapping("/main")
    public String mainPage() {
        return "main";
    }
}
