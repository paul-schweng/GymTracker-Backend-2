package de.dhbw.cleanproject.adapter.exercise;

import de.dhbw.cleanproject.adapter.exercise.dto.ActualExerciseDTO;
import de.dhbw.cleanproject.domain.exercise.ActualExercise;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ActualDtoToActualExerciseMapper implements Function<ActualExerciseDTO, ActualExercise> {

    @Override
    public ActualExercise apply(ActualExerciseDTO actualExerciseDTO) {
        return map(actualExerciseDTO);
    }

    private ActualExercise map(ActualExerciseDTO actualExerciseDTO) {
        return ActualExercise.builder()
                .actualSets(actualExerciseDTO.getActualSets())
                .actualReps(actualExerciseDTO.getActualReps())
                .actualWeight(actualExerciseDTO.getActualWeight())
                .date(actualExerciseDTO.getDate())
                .build();
    }
}
