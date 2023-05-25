package de.dhbw.cleanproject.domain.trainingplan;


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
    @Column(name = "id", nullable = false)
    @GenericGenerator(name = "client_id", strategy = "de.dhbw.cleanproject.abstractioncode.JpaIdGenerator")
    @GeneratedValue(generator = "client_id")
    @Type(type="uuid-char")
    private UUID id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Exercise> mondayExercises;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Exercise> tuesdayExercises;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Exercise> wednesdayExercises;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Exercise> thursdayExercises;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Exercise> fridayExercises;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Exercise> saturdayExercises;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Exercise> sundayExercises;

    private String name;
    private Integer duration;
    private String startDate;
    private String endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

}
