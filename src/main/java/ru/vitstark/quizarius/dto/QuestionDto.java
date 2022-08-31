package ru.vitstark.quizarius.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vitstark.quizarius.models.Question;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDto {
    private String bodyOfQuestion;
    private String[] answers;
    private String correctAnswer;
    private Question.Theme theme;
}
