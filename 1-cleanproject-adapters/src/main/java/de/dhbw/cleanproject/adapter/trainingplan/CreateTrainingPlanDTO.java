package de.dhbw.cleanproject.adapter.trainingplan;

import de.dhbw.cleanproject.adapter.exercise.ExerciseDTO;
import de.dhbw.cleanproject.domain.exercise.Exercise;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;


@Data
public class CreateTrainingPlanDTO {

    private ExercisesDTO exercises;


    private String name;

    /**
     * Duration in weeks
     */
    private Integer duration;
    private String startDate;
    private String endDate;

}

@Data
class ExercisesDTO {
    @NotNull
    private List<Exercise> monday, tuesday, wednesday, thursday, friday, saturday, sunday;
}
