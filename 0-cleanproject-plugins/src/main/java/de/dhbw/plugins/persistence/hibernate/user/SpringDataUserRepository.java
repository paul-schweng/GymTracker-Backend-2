package de.dhbw.plugins.persistence.hibernate.user;

import de.dhbw.cleanproject.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataUserRepository extends JpaRepository<User, UUID> {

    User findByUsername(String username);
    boolean existsByUsername(String username);
}
