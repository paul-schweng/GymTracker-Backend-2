package de.dhbw.cleanproject.domain.user;

public interface UserRepository {

    public User findByUsername(String username);
    public boolean existsByUsername(String username);

}
