package ru.vitstark.quizarius.util.question;

import ru.vitstark.quizarius.dto.QuestionDto;
import ru.vitstark.quizarius.models.Question;

public interface GameSessionGenerator {
    QuestionDto generate(Question question);
}
