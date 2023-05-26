package de.dhbw.cleanproject.adapter.trainingplan;

import de.dhbw.cleanproject.adapter.exercise.ExerciseResource;
import de.dhbw.cleanproject.domain.exercise.Exercise;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
@Builder
public class TrainingPlanResource {
    private final UUID id;

    private final ExercisesResource exercises;

    private final String name;
    private final Integer duration;
    private final LocalDate startDate, endDate;
}

@Data
@Builder
class ExercisesResource {
    private List<ExerciseResource> monday, tuesday, wednesday, thursday, friday, saturday, sunday;
}
