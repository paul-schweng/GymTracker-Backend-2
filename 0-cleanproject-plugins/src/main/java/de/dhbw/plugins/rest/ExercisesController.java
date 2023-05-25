package de.dhbw.plugins.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exercises")
@RequiredArgsConstructor
public class ExercisesController {


    /**
     * Get all exercises with the given name
     * Case 1: Exercise is from User -> return exercise with all data
     * Case 2: Exercise is not from user -> return exercise with only name
     * @param partialName partial name of the exercise
     * @return List of DateExerciseData
     */
    @GetMapping("/autocomplete")
    public ResponseEntity<?> getAllLatestExercisesByName(@RequestParam("q") String partialName){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getExercises(){
        return new ResponseEntity<>(HttpStatus.OK);
    }

}