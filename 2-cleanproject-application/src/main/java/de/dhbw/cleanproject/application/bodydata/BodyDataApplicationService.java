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

        existingBodyData.addMeasurements(bodyDataMeasurements);



        // save the updated BodyData back to the database
        bodyDataRepository.save(existingBodyData);

    }

    @Override
    public BodyData getBodyData() {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return bodyDataRepository.getBodyData(principal.getId());
    }



}
