package de.dhbw.cleanproject.domain.bodydata;

import de.dhbw.cleanproject.domain.user.User;

import java.util.UUID;

public interface BodyDataRepository {

    void save(BodyData bodyData);
    BodyData getBodyData(UUID userId);

}
