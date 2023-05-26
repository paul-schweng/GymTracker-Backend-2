package de.dhbw.cleanproject.domain.exercise;

import de.dhbw.cleanproject.domain.exercise.data.DateExerciseData;

import java.util.List;
import java.util.Map;

public interface ExerciseApplication {

    List<Exercise> getAllLatestExercisesByName(String partialName);

    Map<String, List<DateExerciseData>> getExercises();
}
