package de.dhbw.cleanproject.domain.trainingplan;

import java.util.List;
import java.util.UUID;

public interface TrainingPlanApplication {

    void addTrainingPlan(TrainingPlan newTrainingPlan);

    void deleteTrainingPlan(UUID trainingPlanId);

    void updateTrainingPlan(TrainingPlan trainingPlan);

    List<TrainingPlan> getTrainingPlans();

}
