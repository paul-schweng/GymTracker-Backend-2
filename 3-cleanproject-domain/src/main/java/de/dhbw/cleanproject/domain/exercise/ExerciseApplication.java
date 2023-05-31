package de.dhbw.cleanproject.domain.exercise;

import de.dhbw.cleanproject.domain.exercise.data.ActualExerciseDTO;
import de.dhbw.cleanproject.domain.exercise.data.DateExerciseData;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public interface ExerciseApplication {

    List<Exercise> getAllLatestExercisesByName(String partialName);

    Map<String, List<DateExerciseData>> getExercises();

    void addActualExercise(UUID exerciseId, ActualExercise actualExercise);

    Set<ActualExerciseDTO> getActualExercises(LocalDate date);
}
