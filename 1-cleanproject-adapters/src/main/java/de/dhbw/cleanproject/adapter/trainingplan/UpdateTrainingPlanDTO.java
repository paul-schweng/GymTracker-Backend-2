package de.dhbw.cleanproject.adapter.trainingplan;

import de.dhbw.cleanproject.domain.exercise.Exercise;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class UpdateTrainingPlanDTO {

    private UUID id;

    private List<Exercise> monday, tuesday, wednesday, thursday, friday, saturday, sunday;


    private String name;

    /**
     * Duration in weeks
     */
    private Integer duration;
    private String startDate;
    private String endDate;

}
