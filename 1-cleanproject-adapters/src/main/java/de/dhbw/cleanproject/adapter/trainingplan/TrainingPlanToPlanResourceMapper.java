package de.dhbw.cleanproject.adapter.trainingplan;

import de.dhbw.cleanproject.adapter.exercise.ExerciseToExerciseResourceMapper;
import de.dhbw.cleanproject.domain.trainingplan.TrainingPlan;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TrainingPlanToPlanResourceMapper implements Function<TrainingPlan, TrainingPlanResource> {

    private final ExerciseToExerciseResourceMapper exerciseToExerciseResourceMapper;

    @Override
    public TrainingPlanResource apply(TrainingPlan trainingPlan) {
        return map(trainingPlan);
    }

    private TrainingPlanResource map(TrainingPlan trainingPlan) {
        ExercisesResource exercises = ExercisesResource.builder()
                .monday(trainingPlan.getMondayExercises().stream().map(exerciseToExerciseResourceMapper).collect(Collectors.toList()))
                .tuesday(trainingPlan.getTuesdayExercises().stream().map(exerciseToExerciseResourceMapper).collect(Collectors.toList()))
                .wednesday(trainingPlan.getWednesdayExercises().stream().map(exerciseToExerciseResourceMapper).collect(Collectors.toList()))
                .thursday(trainingPlan.getThursdayExercises().stream().map(exerciseToExerciseResourceMapper).collect(Collectors.toList()))
                .friday(trainingPlan.getFridayExercises().stream().map(exerciseToExerciseResourceMapper).collect(Collectors.toList()))
                .saturday(trainingPlan.getSaturdayExercises().stream().map(exerciseToExerciseResourceMapper).collect(Collectors.toList()))
                .sunday(trainingPlan.getSundayExercises().stream().map(exerciseToExerciseResourceMapper).collect(Collectors.toList()))
                .build();

        return TrainingPlanResource.builder()
                .id(trainingPlan.getId())
                .name(trainingPlan.getName())
                .duration(trainingPlan.getDuration())
                .startDate(trainingPlan.getStartDate())
                .endDate(trainingPlan.getEndDate())
                .exercises(exercises)
                .build();
    }
}
