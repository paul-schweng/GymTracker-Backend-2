package de.dhbw.cleanproject.adapter.exercise.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ExerciseDTO {

    private String name;
    private Integer sets;
    private Integer reps;
    private Double weight;
}
