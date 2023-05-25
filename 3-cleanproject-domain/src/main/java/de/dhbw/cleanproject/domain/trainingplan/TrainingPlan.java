package de.dhbw.cleanproject.domain.trainingplan;


import de.dhbw.cleanproject.domain.exception.CustomException;
import de.dhbw.cleanproject.domain.exercise.Exercise;
import de.dhbw.cleanproject.domain.user.User;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

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

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name="training_plan_monday")
    private List<Exercise> mondayExercises;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name="training_plan_tuesday")
    private List<Exercise> tuesdayExercises;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name="training_plan_wednesday")
    private List<Exercise> wednesdayExercises;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name="training_plan_thursday")
    private List<Exercise> thursdayExercises;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name="training_plan_friday")
    private List<Exercise> fridayExercises;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name="training_plan_saturday")
    private List<Exercise> saturdayExercises;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name="training_plan_sunday")
    private List<Exercise> sundayExercises;



    private String name;

    /**
     * Duration in weeks
     */
    private Integer duration;
    private String startDate;
    private String endDate;

    @ManyToOne
    private User user;


}
