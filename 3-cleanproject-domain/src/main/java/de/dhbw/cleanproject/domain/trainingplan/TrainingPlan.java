package de.dhbw.cleanproject.domain.trainingplan;


import de.dhbw.cleanproject.domain.exercise.Exercise;
import de.dhbw.cleanproject.domain.user.User;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
@Entity
public class TrainingPlan {
    @Id
    @Column(name = "id")
    @GenericGenerator(name = "client_id", strategy = "de.dhbw.cleanproject.abstractioncode.JpaIdGenerator")
    @GeneratedValue(generator = "client_id", strategy = GenerationType.AUTO)
    @Type(type="uuid-char")
    private UUID id;

    @OneToMany(cascade = {CascadeType.ALL}, orphanRemoval = true)
    @JoinTable(name="training_plan_monday")
    private List<Exercise> mondayExercises;

    @OneToMany(cascade = {CascadeType.ALL}, orphanRemoval = true)
    @JoinTable(name="training_plan_tuesday")
    private List<Exercise> tuesdayExercises;

    @OneToMany(cascade = {CascadeType.ALL}, orphanRemoval = true)
    @JoinTable(name="training_plan_wednesday")
    private List<Exercise> wednesdayExercises;

    @OneToMany(cascade = {CascadeType.ALL}, orphanRemoval = true)
    @JoinTable(name="training_plan_thursday")
    private List<Exercise> thursdayExercises;

    @OneToMany(cascade = {CascadeType.ALL}, orphanRemoval = true)
    @JoinTable(name="training_plan_friday")
    private List<Exercise> fridayExercises;

    @OneToMany(cascade = {CascadeType.ALL}, orphanRemoval = true)
    @JoinTable(name="training_plan_saturday")
    private List<Exercise> saturdayExercises;

    @OneToMany(cascade = {CascadeType.ALL}, orphanRemoval = true)
    @JoinTable(name="training_plan_sunday")
    private List<Exercise> sundayExercises;



    private String name;

    /**
     * Duration in weeks
     */
    private Integer duration;
    private LocalDate startDate;
    private LocalDate endDate;

    @ManyToOne
    private User user;

    public List<Exercise> getAllExercises() {
        return Stream.of(mondayExercises, tuesdayExercises, wednesdayExercises, thursdayExercises, fridayExercises, saturdayExercises, sundayExercises)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

}
