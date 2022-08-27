package ru.vitstark.quizarius.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
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

    @OneToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Person person;
}
