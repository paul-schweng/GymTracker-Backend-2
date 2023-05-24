package de.dhbw.plugins.persistence.hibernate.bodydata;

import de.dhbw.cleanproject.domain.bodydata.BodyData;
import de.dhbw.cleanproject.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataBodyDataRepository extends JpaRepository<BodyData, UUID> {




}
