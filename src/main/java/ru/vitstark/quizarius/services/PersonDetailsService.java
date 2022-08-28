package ru.vitstark.quizarius.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.vitstark.quizarius.models.Person;
import ru.vitstark.quizarius.security.PersonDetails;

import java.util.Optional;

@Service
public class PersonDetailsService implements UserDetailsService {
    private final PeopleService peopleService;

    @Autowired
    public PersonDetailsService(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Person> person = peopleService.findByEmail(email);

        if (person.isEmpty()) {
            throw new UsernameNotFoundException("Человек с таким адресом электронной почты не найден!");
        }

        return new PersonDetails(person.get());
    }
}
