package de.dhbw.cleanproject.adapter.trainingplan.resource;

import de.dhbw.cleanproject.adapter.exercise.resource.ExerciseResource;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ExercisesResource {
    private List<ExerciseResource> monday, tuesday, wednesday, thursday, friday, saturday, sunday;
}
