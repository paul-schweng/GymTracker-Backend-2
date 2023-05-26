package de.dhbw.cleanproject.domain.exercise;

import java.util.List;

public interface ExerciseRepository {

    List<Exercise> getAllExercisesByPartialName(String partialName);

    List<Exercise> getExercises();

}

