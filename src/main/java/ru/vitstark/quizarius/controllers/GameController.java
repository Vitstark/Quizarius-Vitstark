package ru.vitstark.quizarius.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vitstark.quizarius.security.PersonDetails;

@Controller
@RequestMapping("/game")
public class GameController {

    @GetMapping("/menu")
    public String menuPage(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) auth.getPrincipal();

        model.addAttribute("user", personDetails.getPerson());
        return "game/menu";
    }

    @GetMapping("/person")
    public String personStatisticPage(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) auth.getPrincipal();

        model.addAttribute("statistic", personDetails.getPerson().getStatistic());
        return "game/stats";
    }

    @GetMapping("/choosing")
    public String personStaticPage(@ModelAttribute("theme") String theme) {
        return "game/choosing";
    }
}
