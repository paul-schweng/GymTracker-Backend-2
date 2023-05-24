package de.dhbw.plugins.persistence.hibernate.trainingplan;

import de.dhbw.cleanproject.domain.trainingplan.TrainingPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataTrainingPlanRepository extends JpaRepository<TrainingPlan, UUID> {



}
