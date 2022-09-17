package ru.vitstark.quizarius.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import ru.vitstark.quizarius.dto.PersonSignUpForm;
import ru.vitstark.quizarius.models.Person;
import ru.vitstark.quizarius.models.Statistic;
import ru.vitstark.quizarius.reposities.PeopleRepository;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class PeopleServiceTests {
    @Autowired
    private PeopleRepository peopleRepository;

    @Autowired
    private PeopleService peopleService;

    private static Person testPerson;

    @BeforeAll
    static void beforeAll() {
        Statistic statistic = new Statistic(1l, 2, 1, null);

        testPerson = Person.builder()
                .id(1l)
                .email("vitstark@mail.ru")
                .username("vitstark")
                .role(Person.Role.ADMIN)
                .status(Person.Status.ACTIVE)
                .password("123")
                .statistic(statistic)
                .build();

        statistic.setPerson(testPerson);
    }

    @Test
    void OptionalShouldBePresent() {
        assertTrue(peopleService.findById(1l).isPresent());
    }

    @Test
    void personShouldBeFoundById() {
        assertEquals(testPerson, peopleService.findById(1l).get());
    }

    @Test
    void personShouldBeFoundByUsername() {
        assertEquals(testPerson, peopleService.findByUsername("vitstark").get());
    }

    @Test
    void personShouldBeFoundByEmail() {
        assertEquals(testPerson, peopleService.findByEmail("vitstark@mail.ru").get());
    }

    @Test
    void registerShouldSavePersonInDB() {
        PersonSignUpForm personSignUpForm = new PersonSignUpForm("test@mail.ru",
                "testPerson", "22213");

        peopleService.signUp(personSignUpForm);
        Person person = peopleRepository.findByUsername("testPerson").get();

        assertEquals("test@mail.ru", person.getEmail());
        assertEquals("testPerson", person.getUsername());

        peopleRepository.deleteById(person.getId());
    }
}
