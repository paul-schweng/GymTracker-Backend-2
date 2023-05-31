package de.dhbw.cleanproject.domain.bodydata;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor(force = true)
@AllArgsConstructor
@Getter
@Setter(AccessLevel.PRIVATE)
@Entity
@Builder
public class BodyPart {

    @Id
    @GenericGenerator(name = "client_id", strategy = "de.dhbw.cleanproject.abstractioncode.JpaIdGenerator")
    @GeneratedValue(generator = "client_id")
    @Type(type="uuid-char")
    private UUID id;

    @Enumerated(EnumType.STRING)
    private final BodyPartType type;  // Enum for BodyPartType

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<BodyMeasurement> measurements;

    @Column(name = "side")
    @Enumerated(EnumType.STRING)
    private final BodySide side;

}
