package de.dhbw.plugins.persistence.hibernate.exercise;

import de.dhbw.cleanproject.domain.exercise.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataExerciseRepository extends JpaRepository<Exercise, UUID> {




}
