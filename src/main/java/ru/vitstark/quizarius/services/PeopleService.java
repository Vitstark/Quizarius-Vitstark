package ru.vitstark.quizarius.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vitstark.quizarius.dto.PersonSignUpForm;
import ru.vitstark.quizarius.models.Person;
import ru.vitstark.quizarius.models.Statistic;
import ru.vitstark.quizarius.reposities.PeopleRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PeopleService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> findAll() {
        return peopleRepository.findAll();
    }

    public void signUp(PersonSignUpForm form) {
        Person person = Person.builder()
                .email(form.getEmail())
                .username(form.getUsername())
                .password(form.getPassword())
                .statistic(new Statistic())
                .build();

        peopleRepository.save(person);
    }

    public Optional<Person> findById(Long id) {
        return peopleRepository.findById(id);
    }

    public Optional<Person> findByUsername(String username) {
        return peopleRepository.findByUsername(username);
    }

    public Optional<Person> findByEmail(String email) {
        return peopleRepository.findByEmail(email);
    }

    public void update(Person person) {
        peopleRepository.save(person);
    }

    public void delete(Long id) {
        peopleRepository.deleteById(id);
    }
}
