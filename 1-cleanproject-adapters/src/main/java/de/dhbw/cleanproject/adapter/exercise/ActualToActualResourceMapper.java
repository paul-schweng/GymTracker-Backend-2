package de.dhbw.cleanproject.adapter.exercise;

import de.dhbw.cleanproject.adapter.exercise.resource.ActualExerciseResource;
import de.dhbw.cleanproject.domain.exercise.ActualExercise;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ActualToActualResourceMapper implements Function<ActualExercise, ActualExerciseResource> {

    @Override
    public ActualExerciseResource apply(ActualExercise actualExercise) {
        return map(actualExercise);
    }

    private ActualExerciseResource map(ActualExercise actualExercise) {
        return ActualExerciseResource.builder()
                .actualSets(actualExercise.getActualSets())
                .actualReps(actualExercise.getActualReps())
                .actualWeight(actualExercise.getActualWeight())
                .date(actualExercise.getDate())
                .id(actualExercise.getExerciseId())
                .build();
    }
}
