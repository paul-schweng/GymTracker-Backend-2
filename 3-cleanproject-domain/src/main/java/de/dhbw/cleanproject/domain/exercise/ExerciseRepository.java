package de.dhbw.cleanproject.domain.exercise;

import java.util.List;
import java.util.UUID;

public interface ExerciseRepository {

    List<Exercise> getAllExercisesByPartialName(String partialName);

    Exercise getExerciseById(UUID id);
    List<Exercise> getExercisesOfUser();

    void save(Exercise exercise);
}

