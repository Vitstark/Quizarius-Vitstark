package ru.vitstark.quizarius.util.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.vitstark.quizarius.dto.PersonSignUpForm;
import ru.vitstark.quizarius.models.Person;
import ru.vitstark.quizarius.services.PeopleService;

@Component
public class PersonSignUpFormValidator implements Validator {
    private final PeopleService peopleService;

    @Autowired
    public PersonSignUpFormValidator(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        PersonSignUpForm form = (PersonSignUpForm) target;

        if (peopleService.findByEmail(form.getEmail()).isPresent()) {
            errors.rejectValue("email", "Пользователь с этой электронной почтой уже существует");
        }
    }
}
