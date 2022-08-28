package ru.vitstark.quizarius.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import ru.vitstark.quizarius.models.Question;
import ru.vitstark.quizarius.models.enums.Theme;

import java.util.List;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class QuestionServiceTests {
    @Autowired
    private QuestionsService questionsService;

    private static Question testQuestion;

    @BeforeAll
    static void beforeAll() {
        String[] unCorrectAnswers = new String[]{"13 сентября","4 декабря",
                "3 сентября","11 сентября","12 октября","13 августа"};

        testQuestion = Question.builder()
                .id(1l)
                .theme(Theme.PROGRAMMING)
                .bodyOfQuestion("Какого числа отмечается День программиста (256 день в году) в високосный год?")
                .correctAnswer("12 сентября")
                .unCorrectAnswers(unCorrectAnswers)
                .build();
    }

    @Test
    void findAllByThemeShouldReturnOnlyOneTheme() {
        List<Question> questions = questionsService.findAllByThemeIs(Theme.HISTORY);

        boolean hasOnlyOneTheme = questions.stream()
                .allMatch(question -> question.getTheme().equals(Theme.HISTORY));

        Assertions.assertTrue(hasOnlyOneTheme);
    }

    @Test
    void findByIdShouldReturnCorrectQuestion() {
        Assertions.assertEquals(testQuestion, questionsService.findById(1l).get());
    }
}
