package de.dhbw.plugins.persistence.hibernate.exercise;

import de.dhbw.cleanproject.domain.exception.CustomException;
import de.dhbw.cleanproject.domain.exercise.Exercise;
import de.dhbw.cleanproject.domain.exercise.ExerciseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ExerciseRepositoryBridge implements ExerciseRepository {

    private final SpringDataExerciseRepository springDataExerciseRepository;

    @Override
    public List<Exercise> getAllExercisesByPartialName(String partialName) {
        return springDataExerciseRepository.findAllByNameContainingIgnoreCase(partialName);
    }

    @Override
    public Exercise getExerciseById(UUID id) {
        return springDataExerciseRepository.findById(id)
                .orElseThrow(() -> new CustomException("Exercise not found", "Does not exist"));
    }

    @Override
    public List<Exercise> getExercisesOfUser() {
        return null;
    }

    @Override
    public void save(Exercise exercise) {
        springDataExerciseRepository.save(exercise);
    }
}
