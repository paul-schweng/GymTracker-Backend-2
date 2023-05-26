package de.dhbw.cleanproject.adapter.trainingplan.resource;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
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


