package de.dhbw.cleanproject.adapter.trainingplan;

import de.dhbw.cleanproject.adapter.exercise.ExerciseDTO;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
class ExercisesDTO {
    @NotNull
    private List<ExerciseDTO> monday, tuesday, wednesday, thursday, friday, saturday, sunday;
}
