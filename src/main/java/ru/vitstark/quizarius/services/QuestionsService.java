package ru.vitstark.quizarius.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vitstark.quizarius.models.Question;
import ru.vitstark.quizarius.models.enums.Theme;
import ru.vitstark.quizarius.reposities.QuestionsRepository;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionsService {
    private final QuestionsRepository questionsRepository;

    @Autowired
    public QuestionsService(QuestionsRepository questionsRepository) {
        this.questionsRepository = questionsRepository;
    }

    public List<Question> findAll() {
        return questionsRepository.findAll();
    }

    public List<Question> findAllByThemeIs(Theme theme) {
        return questionsRepository.findAllByThemeIs(theme);
    }

    public Optional<Question> findById(Long id) {
        return questionsRepository.findById(id);
    }
}
