package ru.vitstark.quizarius.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vitstark.quizarius.dto.PersonSignUpForm;
import ru.vitstark.quizarius.services.PeopleService;
import ru.vitstark.quizarius.util.validators.PersonSignUpFormValidator;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private final PeopleService peopleService;
    private final PersonSignUpFormValidator validator;

    @Autowired
    public AuthController(PeopleService peopleService, PersonSignUpFormValidator validator) {
        this.peopleService = peopleService;
        this.validator = validator;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("form") PersonSignUpForm form) {
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String registrationProcessing(@ModelAttribute("form") @Valid PersonSignUpForm form,
                                         Errors errors) {
        validator.validate(form, errors);

        if (errors.hasErrors()) {
            return "auth/registration";
        }

        peopleService.signUp(form);
        return "redirect:/auth/login";
    }
}
