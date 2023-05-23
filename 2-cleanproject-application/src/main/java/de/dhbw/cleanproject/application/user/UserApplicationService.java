package de.dhbw.cleanproject.application.user;

import de.dhbw.cleanproject.domain.exception.CustomException;
import de.dhbw.cleanproject.domain.user.User;
import de.dhbw.cleanproject.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserApplicationService implements UserApplication {

    private final UserRepository userRepository;



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

        System.out.println(UUID.randomUUID());
        User user = User.builder()
                .id(UUID.randomUUID())
                .name(registerUser.getName())
                .username(registerUser.getUsername())
                .password(registerUser.getPassword())
                .build();

        userRepository.save(user);
    }


    @Override
    public User encryptPassword(User rawPasswordUser, PasswordEncoder passwordEncoder) {
        return User.builder()
                .name(rawPasswordUser.getName())
                .username(rawPasswordUser.getUsername())
                .password(passwordEncoder.encode(rawPasswordUser.getPassword()))
                .build();
    }


}
