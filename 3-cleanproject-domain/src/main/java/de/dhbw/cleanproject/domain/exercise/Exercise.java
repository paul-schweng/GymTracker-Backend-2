package de.dhbw.cleanproject.domain.exercise;

import com.sun.istack.NotNull;
import de.dhbw.cleanproject.domain.trainingplan.TrainingPlan;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

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

}
