package de.dhbw.cleanproject.adapter.mapper;

import de.dhbw.cleanproject.application.exercise.data.DateExerciseData;
import de.dhbw.cleanproject.domain.exercise.Exercise;
import org.springframework.stereotype.Component;

@Component
public class ExerciseToDateExerciseMapper {

    public DateExerciseData apply(Exercise exercise, String date){
        return DateExerciseData.builder()
                .name(exercise.getName())
                .sets(exercise.getSets())
                .reps(exercise.getReps())
                .weight(exercise.getWeight())
                .date(date)
                .build();
    }

}
