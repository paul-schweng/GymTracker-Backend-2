package de.dhbw.plugins.rest.security;

import de.dhbw.cleanproject.adapter.mapper.UserToUserResourceMapper;
import de.dhbw.cleanproject.adapter.resource.UserResource;
import de.dhbw.cleanproject.application.user.UserApplication;
import de.dhbw.cleanproject.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserApplication userApplication;
    private final UserToUserResourceMapper userToUserResourceMapper;
    private final PasswordEncoder passwordEncoder;


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User registerUser){
        User encryptedPasswordUser = userApplication.encryptPassword(registerUser, passwordEncoder);
        userApplication.createUser(encryptedPasswordUser);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/available")
    public ResponseEntity<?> isUsernameAvailable(@RequestParam("username") String username){
        boolean exists = userApplication.existsByUsername(username);
        Map<String, Boolean> map = new HashMap<>();
        map.put("available", !exists);

        return ResponseEntity.ok(map);
    }

    @GetMapping("/iAm")
    public ResponseEntity<?> getUser(){
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserResource user = userToUserResourceMapper.apply(principal);

        return ResponseEntity.ok(user);
    }

}


