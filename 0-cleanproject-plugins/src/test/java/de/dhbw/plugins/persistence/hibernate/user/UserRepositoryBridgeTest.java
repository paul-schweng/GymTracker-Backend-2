package de.dhbw.plugins.persistence.hibernate.user;

import de.dhbw.cleanproject.domain.user.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserRepositoryBridgeTest {

    @Mock
    private SpringDataUserRepository springDataUserRepository;

    @InjectMocks
    private UserRepositoryBridge userRepositoryBridge;

    private User user;
    private final String username = "testUser";
    private final UUID id = UUID.randomUUID();

    @BeforeEach
    void setUp() {
        user = User.builder()
                .id(id)
                .username(username)
                .password("testPassword")
                .build();
    }

    @Test
    void findByUsernameTest() {
        when(springDataUserRepository.findByUsername(username)).thenReturn(user);
        User resultUser = userRepositoryBridge.findByUsername(username);
        assertEquals(user, resultUser);
        verify(springDataUserRepository, times(1)).findByUsername(username);
    }

    @Test
    void existsByUsernameTest() {
        when(springDataUserRepository.existsByUsername(username)).thenReturn(true);
        boolean exists = userRepositoryBridge.existsByUsername(username);
        Assertions.assertTrue(exists);
        verify(springDataUserRepository, times(1)).existsByUsername(username);
    }

    @Test
    void saveTest() {
        userRepositoryBridge.save(user);
        verify(springDataUserRepository, times(1)).save(user);
    }

    @Test
    void findByIdTest_whenUserExists() {
        when(springDataUserRepository.findById(id)).thenReturn(Optional.of(user));
        User resultUser = userRepositoryBridge.findById(id);
        assertEquals(user, resultUser);
        verify(springDataUserRepository, times(1)).findById(id);
    }

    private void assertEquals(User user, User resultUser) {
        boolean equals = user.getId().equals(resultUser.getId()) &&
                user.getUsername().equals(resultUser.getUsername()) &&
                user.getPassword().equals(resultUser.getPassword());
        Assertions.assertTrue(equals);
    }

    @Test
    void findByIdTest_whenUserDoesNotExist() {
        when(springDataUserRepository.findById(id)).thenReturn(Optional.empty());
        User resultUser = userRepositoryBridge.findById(id);
        Assertions.assertNull(resultUser);
        verify(springDataUserRepository, times(1)).findById(id);
    }
}
