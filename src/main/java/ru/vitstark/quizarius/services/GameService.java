package ru.vitstark.quizarius.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vitstark.quizarius.dto.QuestionDto;
import ru.vitstark.quizarius.models.Person;
import ru.vitstark.quizarius.models.Question;
import ru.vitstark.quizarius.models.Statistic;
import ru.vitstark.quizarius.reposities.PeopleRepository;
import ru.vitstark.quizarius.reposities.QuestionsRepository;
import ru.vitstark.quizarius.util.question.QuestionDtoGenerator;

import java.util.List;
import java.util.Random;

@Service
public class GameService {
    private final QuestionDtoGenerator questionDtoGenerator;
    private final PeopleRepository peopleRepository;
    private final QuestionsRepository questionsRepository;

    @Autowired
    public GameService(QuestionDtoGenerator questionDtoGenerator, PeopleRepository peopleRepository, QuestionsRepository questionsRepository) {
        this.questionDtoGenerator = questionDtoGenerator;
        this.peopleRepository = peopleRepository;
        this.questionsRepository = questionsRepository;
    }

    public QuestionDto createGameSession(Question.Theme theme) {
        List<Question> questionsOnTheme = questionsRepository.findAllByThemeIs(theme);

        Random random = new Random();
        int indexOfQuestion = random.nextInt(questionsOnTheme.size());
        Question question = questionsOnTheme.get(indexOfQuestion);

        return questionDtoGenerator.generate(question);
    }

    public void registrationGameSession(Person person, QuestionDto question, String answer) {
        Statistic statistic = person.getStatistic();
        statistic.setAnswers(statistic.getAnswers() + 1);

        System.out.println(question);
        System.out.println(answer);
        if (question.getCorrectAnswer().equals(answer)) {
            statistic.setCorrectAnswers(statistic.getCorrectAnswers() + 1);
        }

        peopleRepository.save(person);
    }
}
