package de.dhbw.plugins.persistence.hibernate.trainingplan;

import de.dhbw.cleanproject.domain.trainingplan.TrainingPlan;
import de.dhbw.cleanproject.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface SpringDataTrainingPlanRepository extends JpaRepository<TrainingPlan, UUID> {

    List<TrainingPlan> findAllByUser(User user);

    TrainingPlan findByStartDateLessThanEqualAndEndDateGreaterThanAndUser(LocalDate date, LocalDate sameDate, User user);

}
