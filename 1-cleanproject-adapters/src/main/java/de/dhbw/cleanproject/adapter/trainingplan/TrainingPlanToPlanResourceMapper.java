package de.dhbw.cleanproject.adapter.trainingplan;

import de.dhbw.cleanproject.domain.trainingplan.TrainingPlan;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class TrainingPlanToPlanResourceMapper implements Function<TrainingPlan, TrainingPlanResource> {

    @Override
    public TrainingPlanResource apply(TrainingPlan trainingPlan) {
        return map(trainingPlan);
    }

    private TrainingPlanResource map(TrainingPlan trainingPlan) {
        ExercisesResource exercises = ExercisesResource.builder()
                .monday(trainingPlan.getMondayExercises())
                .tuesday(trainingPlan.getTuesdayExercises())
                .wednesday(trainingPlan.getWednesdayExercises())
                .thursday(trainingPlan.getThursdayExercises())
                .friday(trainingPlan.getFridayExercises())
                .saturday(trainingPlan.getSaturdayExercises())
                .sunday(trainingPlan.getSundayExercises())
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
