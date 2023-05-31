package de.dhbw.cleanproject.domain.exercise;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Entity
public class ActualExercise {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private final Integer actualSets;
    private final Integer actualReps;
    private final Integer actualWeight;
    private final LocalDate date;

}

