package de.dhbw.cleanproject.application.exercise;

import de.dhbw.cleanproject.domain.exercise.Exercise;
import de.dhbw.cleanproject.domain.exercise.data.DateExerciseData;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ExerciseToDateExerciseMapper implements Function<Exercise, DateExerciseData> {

    @Override
    public DateExerciseData apply(Exercise exercise){
        return DateExerciseData.builder()
                .name(exercise.getName())
                .sets(exercise.getSets())
                .reps(exercise.getReps())
                .weight(exercise.getWeight())
                .date(exercise.getTrainingPlan().getStartDate())
                .build();
    }

}
