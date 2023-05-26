package de.dhbw.plugins.persistence.hibernate.trainingplan;

import de.dhbw.cleanproject.domain.trainingplan.TrainingPlan;
import de.dhbw.cleanproject.domain.trainingplan.TrainingPlanRepository;
import de.dhbw.cleanproject.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Repository
public class TrainingPlanRepositoryBridge implements TrainingPlanRepository {

    private final SpringDataTrainingPlanRepository repository;


    @Override
    public List<TrainingPlan> findAllByUser(User user) {
        return repository.findAllByUser(user);
    }

    @Override
    public void deleteById(UUID trainingPlanId) {
        repository.deleteById(trainingPlanId);
    }

    @Override
    public void save(TrainingPlan trainingPlan) {
        repository.save(trainingPlan);
    }


    @Override
    public TrainingPlan findById(UUID trainingPlanId) {
        return repository.findById(trainingPlanId).orElse(null);
    }

}
