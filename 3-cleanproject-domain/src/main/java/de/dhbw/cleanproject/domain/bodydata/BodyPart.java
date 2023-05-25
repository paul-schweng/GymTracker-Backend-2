package de.dhbw.cleanproject.domain.bodydata;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class BodyPart {

    @Id
    @GenericGenerator(name = "client_id", strategy = "de.dhbw.cleanproject.abstractioncode.JpaIdGenerator")
    @GeneratedValue(generator = "client_id")
    @Type(type="uuid-char")
    private UUID id;

    @Enumerated(EnumType.STRING)
    private BodyPartType type;  // Enum for BodyPartType

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BodyMeasurement> measurements = new ArrayList<>();

    @Column(name = "side")
    @Enumerated(EnumType.STRING)
    private BodySide side;



    // ... constructors, getters, setters
}
