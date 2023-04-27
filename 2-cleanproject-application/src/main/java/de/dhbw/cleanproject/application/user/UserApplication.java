package de.dhbw.cleanproject.application.user;

import de.dhbw.cleanproject.domain.user.User;

public interface UserApplication {

    public User findByUsername(String username);

}
