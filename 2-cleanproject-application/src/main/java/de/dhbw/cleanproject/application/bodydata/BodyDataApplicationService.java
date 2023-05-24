package de.dhbw.cleanproject.application.bodydata;

import de.dhbw.cleanproject.domain.bodydata.BodyData;
import de.dhbw.cleanproject.domain.bodydata.BodyDataApplication;
import de.dhbw.cleanproject.domain.bodydata.BodyDataRepository;
import de.dhbw.cleanproject.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BodyDataApplicationService implements BodyDataApplication {

    private final BodyDataRepository bodyDataRepository;

    @Override
    public void addMeasurement(BodyData measurement) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        BodyData bodyData = bodyDataRepository.getBodyData(user.getId());

        if (bodyData == null)
            bodyData = BodyData.builder()
                            .user(user)
                            .build();

        bodyData.addMeasurement(measurement);
        bodyDataRepository.save(measurement);
    }

}
