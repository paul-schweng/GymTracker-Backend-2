package de.dhbw.cleanproject.domain.trainingplan;

import de.dhbw.cleanproject.domain.user.User;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface TrainingPlanRepository {

    List<TrainingPlan> findAllByUser(User user);

    void deleteById(UUID trainingPlanId);

    void save(TrainingPlan trainingPlan);

    TrainingPlan findById(UUID trainingPlanId);

    TrainingPlan findByDate(LocalDate date, User user);

}
