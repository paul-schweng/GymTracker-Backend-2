package de.dhbw.cleanproject.adapter.exercise;

import lombok.Data;

import java.util.UUID;

@Data
public class ExerciseDTO {
    private UUID id;
    private String name;
    private Integer sets;
    private Integer reps;
    private Double weight;
}
