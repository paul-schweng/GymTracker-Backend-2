package de.dhbw.cleanproject.adapter.trainingplan;

import de.dhbw.cleanproject.adapter.exercise.ExerciseDtoToExerciseMapper;
import de.dhbw.cleanproject.domain.trainingplan.TrainingPlan;
import de.dhbw.cleanproject.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class UpdatePlanDtoToTrainingPlanMapper implements Function<UpdateTrainingPlanDTO, TrainingPlan> {

    private final ExerciseDtoToExerciseMapper exerciseDtoToExerciseMapper;


    @Override
    public TrainingPlan apply(UpdateTrainingPlanDTO updateTrainingPlanDTO) {
        return map(updateTrainingPlanDTO);
    }


    private TrainingPlan map(UpdateTrainingPlanDTO newPlan) {
        TrainingPlan plan = TrainingPlan.builder()
                .name(newPlan.getName())
                .duration(newPlan.getDuration())
                .endDate(LocalDate.parse(newPlan.getEndDate(), DateTimeFormatter.ISO_DATE))
                .startDate(LocalDate.parse(newPlan.getStartDate(), DateTimeFormatter.ISO_DATE))
                .user((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
                .id(newPlan.getId())
                .build();

        plan.setMondayExercises(newPlan.getExercises().getMonday().stream().map(e -> exerciseDtoToExerciseMapper.apply(e, plan)).collect(java.util.stream.Collectors.toList()));
        plan.setTuesdayExercises(newPlan.getExercises().getTuesday().stream().map(e -> exerciseDtoToExerciseMapper.apply(e, plan)).collect(java.util.stream.Collectors.toList()));
        plan.setWednesdayExercises(newPlan.getExercises().getWednesday().stream().map(e -> exerciseDtoToExerciseMapper.apply(e, plan)).collect(java.util.stream.Collectors.toList()));
        plan.setThursdayExercises(newPlan.getExercises().getThursday().stream().map(e -> exerciseDtoToExerciseMapper.apply(e, plan)).collect(java.util.stream.Collectors.toList()));
        plan.setFridayExercises(newPlan.getExercises().getFriday().stream().map(e -> exerciseDtoToExerciseMapper.apply(e, plan)).collect(java.util.stream.Collectors.toList()));
        plan.setSaturdayExercises(newPlan.getExercises().getSaturday().stream().map(e -> exerciseDtoToExerciseMapper.apply(e, plan)).collect(java.util.stream.Collectors.toList()));
        plan.setSundayExercises(newPlan.getExercises().getSunday().stream().map(e -> exerciseDtoToExerciseMapper.apply(e, plan)).collect(java.util.stream.Collectors.toList()));

        return plan;
    }
}
