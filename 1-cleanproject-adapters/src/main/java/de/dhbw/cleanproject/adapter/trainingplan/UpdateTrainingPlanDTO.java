package de.dhbw.cleanproject.adapter.trainingplan;

import lombok.Data;

import java.util.UUID;

@Data
public class UpdateTrainingPlanDTO {

    private UUID id;

    private ExercisesDTO exercises;


    private String name;

    /**
     * Duration in weeks
     */
    private Integer duration;
    private String startDate;
    private String endDate;

}
