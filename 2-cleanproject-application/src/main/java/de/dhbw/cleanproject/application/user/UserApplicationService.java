package de.dhbw.cleanproject.application.user;

import de.dhbw.cleanproject.domain.exception.CustomException;
import de.dhbw.cleanproject.domain.user.User;
import de.dhbw.cleanproject.domain.user.UserApplication;
import de.dhbw.cleanproject.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
@RequiredArgsConstructor
public class UserApplicationService implements UserApplication {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;



    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public void createUser(User registerUser) {
        if(userRepository.existsByUsername(registerUser.getUsername())){
            throw new CustomException("Username already exists!", "Failed to create user");
        }

        String encryptedPassword = passwordEncoder.encode(registerUser.getPassword());
        registerUser.setPassword(encryptedPassword);

        userRepository.save(registerUser);
    }

    @Override
    public User findById(UUID id) {
        return userRepository.findById(id);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return user;
    }


}
