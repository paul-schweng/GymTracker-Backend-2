package de.dhbw.cleanproject.domain.exercise;

import de.dhbw.cleanproject.domain.exception.CustomException;
import de.dhbw.cleanproject.domain.exercise.data.ActualExerciseDTO;
import de.dhbw.cleanproject.domain.trainingplan.TrainingPlan;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class Exercise {

    @Id
    @GenericGenerator(name = "client_id", strategy = "de.dhbw.cleanproject.abstractioncode.JpaIdGenerator")
    @GeneratedValue(generator = "client_id")
    @Type(type="uuid-char")
    private UUID id;
    private String name;
    private Integer sets;
    private Integer reps;
    private Double weight;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(nullable = false)
    private TrainingPlan trainingPlan;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ActualExercise> actualExercises;

    public void addActualExercise(ActualExercise actualExercise) {
        if(!isActualExerciseDateValid(actualExercise.getDate()))
            throw new CustomException("Date of actual exercise is not valid", "Date invalid!");

        Optional<ActualExercise> duplicateExercise = actualExercises.stream().filter(actual -> actual.getDate().equals(actualExercise.getDate())).findFirst();

        duplicateExercise.ifPresent(exercise -> actualExercises.remove(exercise));

        actualExercises.add(actualExercise);
    }

    public List<ActualExerciseDTO> getActualExercises() {
        return actualExercises.stream()
                .map(actualExercise -> ActualExerciseDTO.builder()
                        .actualReps(actualExercise.getActualReps())
                        .actualSets(actualExercise.getActualSets())
                        .actualWeight(actualExercise.getActualWeight())
                        .date(actualExercise.getDate())
                        .exerciseId(id)
                        .build())
                .collect(Collectors.toList());
    }

    public boolean isActualExerciseDateValid(LocalDate actualExerciseDate) {
        return !actualExerciseDate.isBefore(this.getTrainingPlan().getStartDate()) && actualExerciseDate.isBefore(this.getTrainingPlan().getEndDate());
    }
}
