package ru.vitstark.quizarius.models;

import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_generator_person")
    @SequenceGenerator(name="seq_generator_person",
            sequenceName = "person_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Enumerated(value = EnumType.STRING)
    private Status status;

    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
    @ToString.Exclude
    private Statistic statistic;

    public enum Role {
        USER, ADMIN
    }

    public enum Status {
        ACTIVE, BANNED
    }
}

