package de.dhbw.cleanproject.adapter.trainingplan.dto;

import de.dhbw.cleanproject.adapter.exercise.dto.ExerciseDTO;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class ExercisesDTO {
    @NotNull
    private List<ExerciseDTO> monday, tuesday, wednesday, thursday, friday, saturday, sunday;
}
