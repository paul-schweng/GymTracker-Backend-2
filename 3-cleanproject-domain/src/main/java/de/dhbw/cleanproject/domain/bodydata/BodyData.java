package de.dhbw.cleanproject.domain.bodydata;

import de.dhbw.cleanproject.domain.exception.CustomException;
import de.dhbw.cleanproject.domain.user.User;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class BodyData {

    @Id
    @Type(type="uuid-char")
    private UUID userId;


    @MapsId
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BodyPart> bodyParts = new ArrayList<>();

    public void addMeasurements(BodyData bodyDataMeasurements) {
        for (BodyPartType type : BodyPartType.values()) {
            // if this BodyPartType is dual, we have to handle right and left separately
            if (type.isDual()) {
                addMeasurement(bodyDataMeasurements, type, BodySide.RIGHT);
                addMeasurement(bodyDataMeasurements, type, BodySide.LEFT);
            } else {
                addMeasurement(bodyDataMeasurements, type, BodySide.NONE);
            }
        }
    }


    private void addMeasurement(BodyData bodyDataMeasurements, BodyPartType type, BodySide side){
        Optional<BodyPart> optionalNewBodyPart = bodyDataMeasurements.getBodyParts().stream()
                .filter(bodyPart -> bodyPart.getType().equals(type) && bodyPart.getSide().equals(side))
                .findFirst();

        if (optionalNewBodyPart.isEmpty())
            return;

        BodyPart newBodyPart = optionalNewBodyPart.get();

        // find the existing BodyPart in the existing BodyData's bodyParts list
        Optional<BodyPart> optionalExistingBodyPart = this.getBodyParts().stream()
                .filter(bodyPart -> bodyPart.getType().equals(type) && bodyPart.getSide().equals(side))
                .findFirst();

        // if the existing BodyPart exists
        if (optionalExistingBodyPart.isPresent()) {
            addMeasurementToExistingBodyPart(optionalExistingBodyPart.get(), newBodyPart);
        } else {
            // if the BodyPart does not exist in the existing BodyData
            // add the new BodyPart to the existing BodyData's bodyParts list
            this.getBodyParts().add(newBodyPart);
        }

    }

    private void addMeasurementToExistingBodyPart(BodyPart existingBodyPart, BodyPart newBodyPart) {

        boolean wasMeasurementAddedToday = existingBodyPart.getMeasurements().stream()
                .anyMatch(measurement ->
                        measurement.getDate().equals(newBodyPart.getMeasurements().get(0).getDate())
                );
        if(wasMeasurementAddedToday)
            throw new CustomException("Data already exists for this date. Try again tomorrow", "Could not add data!");

        // add the new measurements to the existing BodyPart
        existingBodyPart.getMeasurements().addAll(newBodyPart.getMeasurements());
    }



/*
    public static BodyData create(User user) {
        return BodyData.builder()
                .bicep(RightLeft.create())
                .forearm(RightLeft.create())
                .leg(RightLeft.create())
                .weight(new ArrayList<>())
                .breast(new ArrayList<>())
                .hip(new ArrayList<>())
                .waist(new ArrayList<>())
                .shoulders(new ArrayList<>())
                .user(user)
                .id(user.getId())
                .build();
    }

 */
}
