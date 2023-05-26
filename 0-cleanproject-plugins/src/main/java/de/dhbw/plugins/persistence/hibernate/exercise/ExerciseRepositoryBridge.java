package de.dhbw.plugins.persistence.hibernate.exercise;

import de.dhbw.cleanproject.domain.exercise.Exercise;
import de.dhbw.cleanproject.domain.exercise.ExerciseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ExerciseRepositoryBridge implements ExerciseRepository {

    private final SpringDataExerciseRepository springDataExerciseRepository;

    @Override
    public List<Exercise> getAllExercisesByPartialName(String partialName) {
        return springDataExerciseRepository.findAllByNameContainingIgnoreCase(partialName);
    }

    @Override
    public List<Exercise> getExercises() {
        return null;
    }
}
