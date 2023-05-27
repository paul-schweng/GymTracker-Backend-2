package de.dhbw.plugins.rest;

import de.dhbw.cleanproject.adapter.exercise.ActualDtoToActualExerciseMapper;
import de.dhbw.cleanproject.adapter.exercise.ActualToActualResourceMapper;
import de.dhbw.cleanproject.adapter.exercise.ExerciseToExerciseResourceMapper;
import de.dhbw.cleanproject.adapter.exercise.dto.ActualExerciseDTO;
import de.dhbw.cleanproject.adapter.exercise.resource.ActualExerciseResource;
import de.dhbw.cleanproject.adapter.exercise.resource.ExerciseResource;
import de.dhbw.cleanproject.domain.exercise.Exercise;
import de.dhbw.cleanproject.domain.exercise.ExerciseApplication;
import de.dhbw.cleanproject.domain.exercise.data.DateExerciseData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/exercises")
@RequiredArgsConstructor
public class ExercisesController {

    private final ExerciseApplication exerciseApplication;
    private final ExerciseToExerciseResourceMapper exerciseToExerciseResourceMapper;
    private final ActualDtoToActualExerciseMapper actualDtoToActualExerciseMapper;
    private final ActualToActualResourceMapper actualToActualResourceMapper;

    /**
     * Get all exercises with the given name
     * Case 1: Exercise is from User -> return exercise with all data
     * Case 2: Exercise is not from user -> return exercise with only name
     * @param partialName partial name of the exercise
     * @return List of DateExerciseData
     */
    @GetMapping("/autocomplete")
    public ResponseEntity<List<ExerciseResource>> getAllLatestExercisesByName(@RequestParam("q") String partialName){
        List<Exercise> exercises = exerciseApplication.getAllLatestExercisesByName(partialName);
        return ResponseEntity.ok(exercises.stream().map(exerciseToExerciseResourceMapper).collect(Collectors.toList()));
    }

    @GetMapping
    public ResponseEntity<Map<String, List<DateExerciseData>>> getExercises(){
        return ResponseEntity.ok(exerciseApplication.getExercises());
    }

    @PostMapping("/{id}/actual-exercises")
    public ResponseEntity<?> createActualExercises(@PathVariable("id") UUID id, @RequestBody ActualExerciseDTO actualExerciseDTO){
        exerciseApplication.addActualExercise(id, actualDtoToActualExerciseMapper.apply(actualExerciseDTO));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/actual-exercises")
    public ResponseEntity<List<ActualExerciseResource>> getActualExercises(@RequestParam("date") String date){
        return ResponseEntity.ok(
                exerciseApplication.getActualExercises(LocalDate.parse(date, DateTimeFormatter.ISO_DATE)).stream()
                        .map(actualToActualResourceMapper)
                        .collect(Collectors.toList())
        );
    }

}
