package ru.vitstark.quizarius.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.vitstark.quizarius.dto.QuestionDto;
import ru.vitstark.quizarius.models.Person;
import ru.vitstark.quizarius.models.Question;
import ru.vitstark.quizarius.security.PersonDetails;
import ru.vitstark.quizarius.services.GameService;

@Controller
@RequestMapping("/game")
public class GameController {

    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

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

    @GetMapping("/play")
    public String gameSession(Model model, @RequestParam("theme") String theme,
                              @ModelAttribute("answer") String answer) {
        QuestionDto questionDto = gameService.createGameSession(Question.Theme.valueOf(theme));

        model.addAttribute("question", questionDto);
        return "game/play";
    }

    @PostMapping("/play")
    public String registerSessionAndCreateNew(Model model, @ModelAttribute("answer") String answer,
                                              @ModelAttribute("question") QuestionDto questionDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails person = (PersonDetails) authentication.getPrincipal();

        gameService.registrationGameSession(person.getPerson(), questionDto, answer);

        questionDto = gameService.createGameSession(questionDto.getTheme());

        model.addAttribute("question", questionDto);
        return "game/play";
    }
}
