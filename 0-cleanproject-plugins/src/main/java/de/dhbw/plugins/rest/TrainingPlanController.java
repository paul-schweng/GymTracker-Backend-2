package de.dhbw.plugins.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/training")
@RequiredArgsConstructor
public class TrainingPlanController {


    @PostMapping
    public ResponseEntity<?> createTrainingPlan(){
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PutMapping
    public ResponseEntity<?> updateTrainingPlan(){
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<?> getTrainingPlans(){
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/{date}/actual-exercises")
    public ResponseEntity<?> getActualExercises(@PathVariable("date") String date){
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PostMapping("/{date}/actual-exercises")
    public ResponseEntity<?> createActualExercises(@PathVariable("date") String date){
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
