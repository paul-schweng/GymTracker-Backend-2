package de.dhbw.plugins.persistence.hibernate.bodydata;

import de.dhbw.cleanproject.domain.bodydata.BodyData;
import de.dhbw.cleanproject.domain.bodydata.BodyDataRepository;
import de.dhbw.cleanproject.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@RequiredArgsConstructor
@Repository
public class BodyDataRepositoryBridge implements BodyDataRepository {

    private final SpringDataBodyDataRepository springDataBodyDataRepository;

    @Override
    public void save(BodyData bodyData) {
        springDataBodyDataRepository.save(bodyData);
    }

    @Override
    public BodyData getBodyData(UUID userId) {
        return springDataBodyDataRepository.findById(userId).orElse(null);
    }
}
