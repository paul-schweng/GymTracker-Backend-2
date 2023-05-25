package de.dhbw.cleanproject.adapter.trainingplan;

import de.dhbw.cleanproject.domain.trainingplan.TrainingPlan;
import de.dhbw.cleanproject.domain.user.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class CreatePlanDtoToTrainingPlanMapper implements Function<CreateTrainingPlanDTO, TrainingPlan> {
    @Override
    public TrainingPlan apply(CreateTrainingPlanDTO createTrainingPlanDTO) {
        return map(createTrainingPlanDTO);
    }

    public TrainingPlan map(CreateTrainingPlanDTO newPlan) {
        return TrainingPlan.builder()
                .name(newPlan.getName())
                .duration(newPlan.getDuration())
                .endDate(newPlan.getEndDate())
                .startDate(newPlan.getStartDate())
                .user((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal())

                .mondayExercises(newPlan.getExercises().getMonday())
                .tuesdayExercises(newPlan.getExercises().getTuesday())
                .wednesdayExercises(newPlan.getExercises().getWednesday())
                .thursdayExercises(newPlan.getExercises().getThursday())
                .fridayExercises(newPlan.getExercises().getFriday())
                .saturdayExercises(newPlan.getExercises().getSaturday())
                .sundayExercises(newPlan.getExercises().getSunday())
                .build();
    }

}
