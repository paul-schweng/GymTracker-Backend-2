package de.dhbw.cleanproject.adapter.exercise.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class ActualExerciseDTO {
    private Integer actualSets;
    private Integer actualReps;
    private Integer actualWeight;
    private LocalDate date;
    private UUID id;
}
