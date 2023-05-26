package de.dhbw.cleanproject.adapter.exercise;

import de.dhbw.cleanproject.domain.exercise.Exercise;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ExerciseToExerciseResourceMapper implements Function<Exercise, ExerciseResource> {

    @Override
    public ExerciseResource apply(Exercise exercise) {
        return map(exercise);
    }

    private ExerciseResource map(Exercise exercise) {
        return ExerciseResource.builder()
                .id(exercise.getId())
                .name(exercise.getName())
                .sets(exercise.getSets())
                .reps(exercise.getReps())
                .weight(exercise.getWeight())
                .build();
    }
}
