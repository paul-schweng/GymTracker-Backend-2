package de.dhbw.cleanproject.domain.bodydata;

import de.dhbw.cleanproject.domain.bodydata.rightleft.Side;
import de.dhbw.cleanproject.domain.exception.CustomException;
import de.dhbw.cleanproject.domain.user.User;
import lombok.*;

import javax.persistence.*;
import java.util.Map;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class BodyData {

    @Id
    private UUID id;

    @MapsId
    @OneToOne
    private User user;

    /*
    @ElementCollection
    private List<TimeSeriesBodyData> weight, breast, hip, waist, shoulders;

    private Side bicep, forearm, leg;
     */

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @MapKeyColumn(name = "body_part")
    private Map<BodyPart, BodyMeasurement> measurements;


    public void addMeasurement(BodyData measurement) {
        for (Map.Entry<BodyPart, BodyMeasurement> entry : measurement.getMeasurements().entrySet()) {
            BodyPart bodyPart = entry.getKey();
            BodyMeasurement bodyMeasurement = entry.getValue();

            // Check if bodyMeasurement has correct number of values
            if (bodyPart.isDual() && !(bodyMeasurement instanceof DualBodyMeasurement)) {
                throw new CustomException("Dual-sided body part must have two values", "Not Acceptable");
            } else if (!bodyPart.isDual() && !(bodyMeasurement instanceof SingleBodyMeasurement)) {
                throw new CustomException("Single-sided body part must have one value", "Not Acceptable");
            }

            // Add measurement to current BodyData
            BodyMeasurement currentMeasurement = this.measurements.get(bodyPart);
            if (currentMeasurement == null) {
                // create new BodyMeasurement depending on the BodyPart
                if (bodyPart.isDual()) {
                    currentMeasurement = new DualBodyMeasurement();
                } else {
                    currentMeasurement = new SingleBodyMeasurement();
                }
                this.measurements.put(bodyPart, currentMeasurement);
            }
            if(bodyPart.isDual()) {
                DualBodyMeasurement dualBodyMeasurement = (DualBodyMeasurement) bodyMeasurement;
                currentMeasurement.addMeasurement(dualBodyMeasurement.getLeft().get(0), Side.LEFT);
                currentMeasurement.addMeasurement(dualBodyMeasurement.getRight().get(0), Side.RIGHT);
            } else {
                SingleBodyMeasurement singleBodyMeasurement = (SingleBodyMeasurement) bodyMeasurement;
                currentMeasurement.addMeasurement(singleBodyMeasurement.getMeasurements().get(0), null);
            }
        }
    }

}
