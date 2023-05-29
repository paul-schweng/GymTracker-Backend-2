package de.dhbw.cleanproject.application.user;

import de.dhbw.cleanproject.domain.exception.CustomException;
import de.dhbw.cleanproject.domain.user.User;
import de.dhbw.cleanproject.domain.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserApplicationServiceTest {

    @Mock
    UserRepository userRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @InjectMocks
    UserApplicationService userApplicationService;

    @Test
    void testExistsByUsername() {
        when(userRepository.existsByUsername("testUser")).thenReturn(true);
        assertTrue(userApplicationService.existsByUsername("testUser"));
    }

    @Test
    void testCreateUser_ExistingUser() {
        User user = new User();
        user.setUsername("existingUser");

        when(userRepository.existsByUsername("existingUser")).thenReturn(true);
        assertThrows(CustomException.class, () -> userApplicationService.createUser(user));
    }

    @Test
    void testCreateUser_NewUser() {
        User user = new User();
        user.setUsername("newUser");
        user.setPassword("password");

        when(userRepository.existsByUsername("newUser")).thenReturn(false);
        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");

        userApplicationService.createUser(user);
        assertEquals("encodedPassword", user.getPassword());

        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testFindById() {
        UUID uuid = UUID.randomUUID();
        User user = new User();
        user.setId(uuid);

        when(userRepository.findById(uuid)).thenReturn(user);
        assertEquals(user, userApplicationService.findById(uuid));
    }

    @Test
    void testLoadUserByUsername_ExistingUser() {
        User user = new User();
        user.setUsername("existingUser");

        when(userRepository.findByUsername("existingUser")).thenReturn(user);
        assertEquals(user, userApplicationService.loadUserByUsername("existingUser"));
    }

    @Test
    void testLoadUserByUsername_NonExistingUser() {
        when(userRepository.findByUsername("nonExistingUser")).thenReturn(null);
        assertThrows(UsernameNotFoundException.class, () -> userApplicationService.loadUserByUsername("nonExistingUser"));
    }
}


