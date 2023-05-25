package de.dhbw.cleanproject.application.bodydata;

import de.dhbw.cleanproject.domain.bodydata.*;
import de.dhbw.cleanproject.domain.bodydata.BodySide;
import de.dhbw.cleanproject.domain.exception.CustomException;
import de.dhbw.cleanproject.domain.user.User;
import de.dhbw.cleanproject.domain.user.UserApplication;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class BodyDataApplicationService implements BodyDataApplication {

    private final BodyDataRepository bodyDataRepository;
    private final UserApplication userApplication;

    @Override
    public void addMeasurement(BodyData bodyDataMeasurements) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userApplication.findById(principal.getId());

        BodyData existingBodyData = bodyDataRepository.getBodyData(user.getId());

        if(existingBodyData == null)
            existingBodyData = BodyData.builder()
                    .user(user)
                    .bodyParts(new ArrayList<>())
                    .build();

        for (BodyPartType type : BodyPartType.values()) {
            // if this BodyPartType is dual, we have to handle right and left separately
            if (type.isDual()) {
                addOrUpdateMeasurement(bodyDataMeasurements, existingBodyData, type, BodySide.RIGHT);
                addOrUpdateMeasurement(bodyDataMeasurements, existingBodyData, type, BodySide.LEFT);
            } else {
                addOrUpdateMeasurement(bodyDataMeasurements, existingBodyData, type, BodySide.NONE);
            }
        }

        // save the updated BodyData back to the database
        bodyDataRepository.save(existingBodyData);

    }

    @Override
    public BodyData getBodyData() {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return bodyDataRepository.getBodyData(principal.getId());
    }

    private void addOrUpdateMeasurement(BodyData bodyDataMeasurements, BodyData existingBodyData, BodyPartType type, BodySide side){
        Optional<BodyPart> optionalNewBodyPart = bodyDataMeasurements.getBodyParts().stream()
                .filter(bodyPart -> bodyPart.getType().equals(type) && bodyPart.getSide().equals(side))
                .findFirst();


        if (optionalNewBodyPart.isEmpty())
            return;

        BodyPart newBodyPart = optionalNewBodyPart.get();

        // find the existing BodyPart in the existing BodyData's bodyParts list
        Optional<BodyPart> optionalExistingBodyPart = existingBodyData.getBodyParts().stream()
                .filter(bodyPart -> bodyPart.getType().equals(type) && bodyPart.getSide().equals(side))
                .findFirst();

        // if the existing BodyPart exists
        if (optionalExistingBodyPart.isPresent()) {
            BodyPart existingBodyPart = optionalExistingBodyPart.get();

            if(existingBodyPart.getMeasurements().stream().anyMatch(measurement -> measurement.getDate().equals(newBodyPart.getMeasurements().get(0).getDate())))
                throw new CustomException("Data already exists for this date. Try again tomorrow", "Could not add data!");

            // add the new measurements to the existing BodyPart
            existingBodyPart.getMeasurements().addAll(newBodyPart.getMeasurements());
        } else {
            // if the BodyPart does not exist in the existing BodyData
            // add the new BodyPart to the existing BodyData's bodyParts list
            existingBodyData.getBodyParts().add(newBodyPart);
        }

    }



}
