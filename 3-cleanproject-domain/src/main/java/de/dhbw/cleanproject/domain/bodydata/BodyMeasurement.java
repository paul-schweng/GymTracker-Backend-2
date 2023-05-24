package de.dhbw.cleanproject.domain.bodydata;

import de.dhbw.cleanproject.domain.bodydata.rightleft.Side;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
@NoArgsConstructor
public abstract class BodyMeasurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Enumerated(EnumType.STRING)
    protected BodyPart bodyPart;

    public BodyMeasurement(BodyPart bodyPart) {
        this.bodyPart = bodyPart;
    }

    public BodyPart getBodyPart() {
        return bodyPart;
    }

    public abstract void addMeasurement(TimeSeriesBodyData data, Side side);


}
