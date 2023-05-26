package de.dhbw.cleanproject.adapter.exercise;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class ExerciseResource {

    private UUID id;
    private String name;
    private Integer sets;
    private Integer reps;
    private Double weight;

}
