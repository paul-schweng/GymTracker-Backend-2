package de.dhbw.cleanproject.adapter.resource;

import de.dhbw.cleanproject.domain.exercise.Exercise;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class TrainingPlanResource {
    private final UUID id;

    private final List<Exercise> mondayExercises;
    private final List<Exercise> tuesdayExercises;
    private final List<Exercise> wednesdayExercises;
    private final List<Exercise> thursdayExercises;
    private final List<Exercise> fridayExercises;
    private final List<Exercise> saturdayExercises;
    private final List<Exercise> sundayExercises;

    private final String name;
    private final Integer duration;
    private final String startDate;
    private final String endDate;
}
