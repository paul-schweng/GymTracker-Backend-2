package de.dhbw.cleanproject.application.exercise;

import de.dhbw.cleanproject.domain.exercise.Exercise;
import de.dhbw.cleanproject.domain.exercise.ExerciseApplication;
import de.dhbw.cleanproject.domain.exercise.ExerciseRepository;
import de.dhbw.cleanproject.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ExerciseApplicationService implements ExerciseApplication {

    private final ExerciseRepository exerciseRepository;

    /**
     * If the user has performed an exercise, the method returns the latest exercise performed by the user. <br>
     * If the user has not performed the exercise, the method returns the latest exercise performed by any user, removing every property but the name of the exercise.
     * @param partialName
     * @return List of Exercises
     */
    @Override
    public List<Exercise> getAllLatestExercisesByName(String partialName) {
        partialName = partialName.trim();

        List<Exercise> allExercises = exerciseRepository.getAllExercisesByPartialName(partialName);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // Group by exercise name
        Map<String, List<Exercise>> groupedExercises = allExercises.stream()
                .collect(Collectors.groupingBy(Exercise::getName));

        // Filter appropriate exercises and collect to list
        List<Exercise> latestExercises = groupedExercises.values().stream()
                .map(exerciseList -> {
                    // First try to find the latest exercise from the user
                    return exerciseList.stream()
                            .filter(exercise -> exercise.getTrainingPlan().getUser().getId().equals(user.getId()))
                            .max(Comparator.comparing(exercise -> exercise.getTrainingPlan().getStartDate()))
                            // If not found, find the latest exercise from other users
                            .orElse(exerciseList.stream()
                                    .max(Comparator.comparing(exercise -> exercise.getTrainingPlan().getStartDate()))
                                    .orElse(null));
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        //remove every property but name if its another user
        List<Exercise> cleanedExercises = latestExercises.stream().map(exercise -> {
            if(!exercise.getTrainingPlan().getUser().getId().equals(user.getId()))
                return Exercise.builder()
                        .name(exercise.getName())
                        .build();

            return exercise;
        }).collect(Collectors.toList());

        return cleanedExercises;
    }

    @Override
    public List<Exercise> getExercises() {
        return null;
    }
}
