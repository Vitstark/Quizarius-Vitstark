package ru.vitstark.quizarius.util.question;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import ru.vitstark.quizarius.dto.QuestionDto;
import ru.vitstark.quizarius.models.Question;

import java.util.*;

@NoArgsConstructor
@Component
public class QuestionDtoGenerator implements GameSessionGenerator {
    public QuestionDto generate(Question question) {
        List<String> sourceOfUnCorrectAnswers = new ArrayList<>(Arrays.asList(question.getUnCorrectAnswers()));
        String[] targetUnCorrectAnswers = new String[4];
        Random random = new Random();

        for (int i = 0; i < targetUnCorrectAnswers.length; i++) {
            int generatedIndex = random.nextInt(sourceOfUnCorrectAnswers.size());
            targetUnCorrectAnswers[i] = sourceOfUnCorrectAnswers.remove(generatedIndex);
        }

        int correctAnswerIndex = random.nextInt(targetUnCorrectAnswers.length);
        targetUnCorrectAnswers[correctAnswerIndex] = question.getCorrectAnswer();

        return QuestionDto.builder()
                .bodyOfQuestion(question.getBodyOfQuestion())
                .answers(targetUnCorrectAnswers)
                .correctAnswer(question.getCorrectAnswer())
                .theme(question.getTheme())
                .build();
    }
}
