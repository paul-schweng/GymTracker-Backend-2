package de.dhbw.cleanproject.application.user;

import de.dhbw.cleanproject.domain.user.User;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface UserApplication {

    User findByUsername(String username);

    boolean existsByUsername(String username);


    void createUser(User registerUser);

}
