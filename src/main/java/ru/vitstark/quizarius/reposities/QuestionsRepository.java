package ru.vitstark.quizarius.reposities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vitstark.quizarius.models.Question;
import ru.vitstark.quizarius.models.Question.Theme;

import java.util.List;

@Repository
public interface QuestionsRepository extends JpaRepository<Question, Long> {
    List<Question> findAllByThemeIs(Theme theme);
}
