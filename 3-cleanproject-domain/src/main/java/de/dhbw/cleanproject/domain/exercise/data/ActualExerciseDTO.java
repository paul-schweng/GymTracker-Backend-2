package de.dhbw.cleanproject.domain.exercise.data;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@Getter
public class ActualExerciseDTO {

    private final Integer actualSets;
    private final Integer actualReps;
    private final Integer actualWeight;
    private final LocalDate date;
    private final UUID exerciseId;

}
