package de.dhbw.cleanproject.domain.user;

import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.UUID;

public interface UserApplication extends UserDetailsService {


    boolean existsByUsername(String username);


    void createUser(User registerUser);

    User findById(UUID id);

}
