package de.dhbw.cleanproject.application.exercise.data;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DateExerciseData {

    private final String name;
    private final Integer sets;
    private final Integer reps;
    private final Double weight;

    private final String date;
}
