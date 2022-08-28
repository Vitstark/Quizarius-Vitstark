package ru.vitstark.quizarius.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vitstark.quizarius.models.Person;
import ru.vitstark.quizarius.services.PeopleService;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private final PeopleService peopleService;

    @Autowired
    public AuthController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @GetMapping("/login")
    public String loginPage(@ModelAttribute("person") Person person) {
        return "login";
    }
}
