package de.dhbw.cleanproject.application.trainingplan;

import de.dhbw.cleanproject.domain.exercise.Exercise;
import de.dhbw.cleanproject.domain.trainingplan.TrainingPlan;
import de.dhbw.cleanproject.domain.trainingplan.TrainingPlanRepository;
import de.dhbw.cleanproject.domain.user.User;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TrainingPlanApplicationServiceTest {

    @Mock
    TrainingPlanRepository trainingPlanRepository;

    @InjectMocks
    TrainingPlanApplicationService trainingPlanApplicationService;

    private final User user = User.builder()
            .id(UUID.randomUUID())
            .build();

    private TrainingPlan trainingPlan;

    @BeforeEach
    public void setup() {
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null);
        SecurityContextHolder.getContext().setAuthentication(auth);

        trainingPlan = TrainingPlan.builder()
                .id(UUID.randomUUID())
                .user(user)
                .duration(2)
                .name("Test")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusWeeks(2))
                .build();
    }

    @AfterEach
    public void clearContext() {
        SecurityContextHolder.clearContext();
    }

    @Test
    void testAddTrainingPlan() {
        trainingPlanApplicationService.addTrainingPlan(trainingPlan);
        verify(trainingPlanRepository, times(1)).save(trainingPlan);
    }

    @Test
    void testDeleteTrainingPlan() {
        when(trainingPlanRepository.findById(trainingPlan.getId())).thenReturn(trainingPlan);
        trainingPlanApplicationService.deleteTrainingPlan(trainingPlan.getId());
        verify(trainingPlanRepository, times(1)).deleteById(trainingPlan.getId());
    }

    @Test
    void testGetTrainingPlanByDate() {
        LocalDate date = LocalDate.now();
        // Set all properties
        when(trainingPlanRepository.findByDate(date, user)).thenReturn(trainingPlan);
        TrainingPlan result = trainingPlanApplicationService.getTrainingPlanByDate(date);
        verify(trainingPlanRepository, times(1)).findByDate(date, user);
        assertTrue(compareTrainingPLan(trainingPlan, result));
    }

    @Test
    void testUpdateTrainingPlan() {
        when(trainingPlanRepository.findById(trainingPlan.getId())).thenReturn(trainingPlan);

        Exercise exercise = Exercise.builder()
                .id(UUID.randomUUID())
                .name("Test")
                .build();

        TrainingPlan updatedPlan = TrainingPlan.builder()
                .id(trainingPlan.getId())
                .user(user)
                .duration(3)
                .name("Test")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusWeeks(3))
                .mondayExercises(new ArrayList<>() {{add(exercise);}})
                .build();

        trainingPlanApplicationService.updateTrainingPlan(updatedPlan);
        verify(trainingPlanRepository, times(1)).save(any(TrainingPlan.class));
    }

    @Test
    void testGetTrainingPlans() {
        when(trainingPlanRepository.findAllByUser(user)).thenReturn(new ArrayList<>());
        trainingPlanApplicationService.getTrainingPlans();
        verify(trainingPlanRepository, times(1)).findAllByUser(user);
    }

    private boolean compareTrainingPLan(TrainingPlan plan1, TrainingPlan plan2) {
        return plan1.getId().equals(plan2.getId()) &&
                plan1.getUser().equals(plan2.getUser()) &&
                plan1.getDuration().equals(plan2.getDuration()) &&
                plan1.getName().equals(plan2.getName()) &&
                plan1.getStartDate().equals(plan2.getStartDate()) &&
                plan1.getEndDate().equals(plan2.getEndDate());
    }
}
