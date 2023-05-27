package de.dhbw.cleanproject.application.trainingplan;

import de.dhbw.cleanproject.domain.exception.CustomException;
import de.dhbw.cleanproject.domain.trainingplan.TrainingPlan;
import de.dhbw.cleanproject.domain.trainingplan.TrainingPlanApplication;
import de.dhbw.cleanproject.domain.trainingplan.TrainingPlanRepository;
import de.dhbw.cleanproject.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;


@RequiredArgsConstructor
@Service
public class TrainingPlanApplicationService implements TrainingPlanApplication {

    private final TrainingPlanRepository trainingPlanRepository;

    @Override
    public void addTrainingPlan(TrainingPlan newtrainingPlan) {
        validateTrainingPlan(newtrainingPlan);
        trainingPlanRepository.save(newtrainingPlan);
    }

    @Override
    public void deleteTrainingPlan(UUID trainingPlanId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        TrainingPlan actualPlan = trainingPlanRepository.findById(trainingPlanId);

        if(actualPlan == null) {
            throw new CustomException("Training Plan not found", "Not Found");
        } else if (!actualPlan.getUser().getId().equals(user.getId())){
            throw new CustomException("Training Plan does not belong to user", "Not Authorized");
        }

        trainingPlanRepository.deleteById(trainingPlanId);
    }

    @Override
    public TrainingPlan getTrainingPlanByDate(LocalDate date) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return trainingPlanRepository.findByDate(date, user);
    }

    @Override
    public void updateTrainingPlan(TrainingPlan updatedPlan) {
        validateTrainingPlan(updatedPlan);

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        TrainingPlan actualPlan = trainingPlanRepository.findById(updatedPlan.getId());

        if(actualPlan == null) {
            throw new CustomException("Training Plan not found", "Not Found");
        } else if (!actualPlan.getUser().getId().equals(user.getId())){
            throw new CustomException("Training Plan does not belong to user", "Not Authorized");
        }

        TrainingPlan plan = TrainingPlan.builder()
                .id(actualPlan.getId())
                .user(user)
                .name(updatedPlan.getName())
                .duration(updatedPlan.getDuration())
                .startDate(updatedPlan.getStartDate())
                .endDate(updatedPlan.getEndDate())

                .mondayExercises(updatedPlan.getMondayExercises())
                .tuesdayExercises(updatedPlan.getTuesdayExercises())
                .wednesdayExercises(updatedPlan.getWednesdayExercises())
                .thursdayExercises(updatedPlan.getThursdayExercises())
                .fridayExercises(updatedPlan.getFridayExercises())
                .saturdayExercises(updatedPlan.getSaturdayExercises())
                .sundayExercises(updatedPlan.getSundayExercises())
                .build();

        trainingPlanRepository.save(plan);

    }

    @Override
    public List<TrainingPlan> getTrainingPlans() {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return trainingPlanRepository.findAllByUser(principal);
    }

    private void validateTrainingPlan(TrainingPlan trainingPlan) {
        // Checking if name is not empty and not null
        if (trainingPlan.getName() == null || trainingPlan.getName().trim().isEmpty()) {
            throw new CustomException("Name of Training Plan cannot be null or empty", "Provide name");
        }

        // Checking if duration is not null and greater than 0
        if (trainingPlan.getDuration() == null || trainingPlan.getDuration() <= 0) {
            throw new CustomException("Duration of Training Plan should be a positive integer", "Duration invalid");
        }

        // Checking if start date and end date are not null
        if (trainingPlan.getStartDate() == null || trainingPlan.getEndDate() == null) {
            throw new CustomException("Start Date and End Date of Training Plan cannot be null", "Invalid dates");
        }

        // Comparing start date and end date
        if (trainingPlan.getStartDate().isAfter(trainingPlan.getEndDate())) {
            throw new CustomException("Start Date of Training Plan cannot be after the End Date", "Invalid dates");
        }

        // Checking if duration in weeks matches the difference between start and end date
        long weeksBetween = ChronoUnit.WEEKS.between(trainingPlan.getStartDate(), trainingPlan.getEndDate());
        if (weeksBetween != trainingPlan.getDuration()) {
            throw new CustomException("Duration doesn't match the difference in weeks between start and end date", "Invalid duration");
        }

    }


}
