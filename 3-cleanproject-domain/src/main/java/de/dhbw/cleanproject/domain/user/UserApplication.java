package de.dhbw.cleanproject.domain.user;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserApplication extends UserDetailsService {

    User findByUsername(String username);

    boolean existsByUsername(String username);


    void createUser(User registerUser);

}
