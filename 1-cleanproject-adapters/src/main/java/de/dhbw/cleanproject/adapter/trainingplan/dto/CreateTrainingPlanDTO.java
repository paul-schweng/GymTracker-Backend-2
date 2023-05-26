package de.dhbw.cleanproject.adapter.trainingplan.dto;

import lombok.Data;


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


