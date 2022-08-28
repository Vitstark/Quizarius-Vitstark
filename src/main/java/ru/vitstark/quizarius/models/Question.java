package ru.vitstark.quizarius.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_id_question")
    @SequenceGenerator(name = "seq_id_question", sequenceName = "question_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private Theme theme;

    @Column(name = "body_of_question")
    private String bodyOfQuestion;

    @Column(name = "correct_answer")
    private String correctAnswer;

    @Type(type = "string-array")
    @Column(name = "uncorrect_answers", columnDefinition = "varchar[]")
    private String[] unCorrectAnswers;

    public enum Theme {
        PROGRAMMING,
        HISTORY,
        SCIENCE
    }
}
