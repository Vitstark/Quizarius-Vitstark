package ru.vitstark.quizarius.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
public class Statistic {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_generator_statistic")
    @SequenceGenerator(name = "seq_generator_statistic",
            sequenceName = "statistic_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "answers")
    private Integer answers;

    @Column(name = "correct_answers")
    private Integer correctAnswers;

    public Statistic() {
        answers = 0;
        correctAnswers = 0;
    }

    @OneToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Person person;
}
