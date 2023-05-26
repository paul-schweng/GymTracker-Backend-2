package de.dhbw.plugins.persistence.hibernate.exercise;

import de.dhbw.cleanproject.domain.exercise.Exercise;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


public interface SpringDataExerciseRepository extends JpaRepository<Exercise, UUID> {


    List<Exercise> findAllByNameContainingIgnoreCase(String name);


}
