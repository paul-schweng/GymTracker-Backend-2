package de.dhbw.cleanproject.adapter.exercise.resource;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Builder
public class ActualExerciseResource {
    private final Integer actualSets;
    private final Integer actualReps;
    private final Integer actualWeight;
    private final LocalDate date;
    private final UUID id;
}
