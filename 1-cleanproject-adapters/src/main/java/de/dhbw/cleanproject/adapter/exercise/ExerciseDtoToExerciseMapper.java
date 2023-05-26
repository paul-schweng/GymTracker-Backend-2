package de.dhbw.cleanproject.adapter.exercise;

import de.dhbw.cleanproject.adapter.exercise.dto.ExerciseDTO;
import de.dhbw.cleanproject.domain.exercise.Exercise;
import de.dhbw.cleanproject.domain.trainingplan.TrainingPlan;
import org.springframework.stereotype.Component;

@Component
public class ExerciseDtoToExerciseMapper {


    public Exercise apply(ExerciseDTO exerciseDTO, TrainingPlan plan) {
        return map(exerciseDTO, plan);
    }

    private Exercise map(ExerciseDTO exerciseDTO, TrainingPlan plan) {
        return Exercise.builder()
                .id(exerciseDTO.getId())
                .name(exerciseDTO.getName())
                .sets(exerciseDTO.getSets())
                .reps(exerciseDTO.getReps())
                .weight(exerciseDTO.getWeight())
                .trainingPlan(plan)
                .build();
    }
}
