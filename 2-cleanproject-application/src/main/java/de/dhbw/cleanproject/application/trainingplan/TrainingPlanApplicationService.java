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
import java.time.format.DateTimeFormatter;
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
        trainingPlanRepository.deleteById(trainingPlanId);
    }

    @Override
    public void updateTrainingPlan(TrainingPlan trainingPlan) {

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
        LocalDate startDate = LocalDate.parse(trainingPlan.getStartDate(), DateTimeFormatter.ISO_DATE);
        LocalDate endDate = LocalDate.parse(trainingPlan.getEndDate(), DateTimeFormatter.ISO_DATE);
        if (startDate.isAfter(endDate)) {
            throw new CustomException("Start Date of Training Plan cannot be after the End Date", "Invalid dates");
        }

        // Checking if duration in weeks matches the difference between start and end date
        long weeksBetween = ChronoUnit.WEEKS.between(startDate, endDate);
        if (weeksBetween != trainingPlan.getDuration()) {
            throw new CustomException("Duration doesn't match the difference in weeks between start and end date", "Invalid duration");
        }

    }


}
