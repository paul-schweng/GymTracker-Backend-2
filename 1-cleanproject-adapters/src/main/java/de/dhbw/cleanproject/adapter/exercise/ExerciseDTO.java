package de.dhbw.cleanproject.adapter.exercise;

import lombok.Data;

@Data
public class ExerciseDTO {
    private String name;
    private Integer sets;
    private Integer reps;
    private Double weight;
}
