package de.dhbw.cleanproject.domain.user;

import java.util.UUID;

public interface UserRepository {

    User findByUsername(String username);
    boolean existsByUsername(String username);

    void save(User user);

    User findById(UUID id);

}
