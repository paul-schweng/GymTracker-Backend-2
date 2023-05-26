package de.dhbw.cleanproject.domain.exercise;

import java.util.List;

public interface ExerciseApplication {

    List<Exercise> getAllLatestExercisesByName(String partialName);

    List<Exercise> getExercises();
}
