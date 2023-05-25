package de.dhbw.plugins.rest;

import de.dhbw.cleanproject.adapter.trainingplan.CreatePlanDtoToTrainingPlanMapper;
import de.dhbw.cleanproject.adapter.trainingplan.CreateTrainingPlanDTO;
import de.dhbw.cleanproject.adapter.trainingplan.TrainingPlanResource;
import de.dhbw.cleanproject.adapter.trainingplan.TrainingPlanToPlanResourceMapper;
import de.dhbw.cleanproject.domain.exercise.ExerciseApplication;
import de.dhbw.cleanproject.domain.trainingplan.TrainingPlan;
import de.dhbw.cleanproject.domain.trainingplan.TrainingPlanApplication;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/training")
@RequiredArgsConstructor
public class TrainingPlanController {

    private final TrainingPlanApplication trainingPlanApplication;
    private final ExerciseApplication exerciseApplication;
    private final CreatePlanDtoToTrainingPlanMapper createPlanDtoToTrainingPlanMapper;
    private final TrainingPlanToPlanResourceMapper trainingPlanToPlanResourceMapper;

    @PostMapping
    public ResponseEntity<?> addTrainingPlan(@RequestBody CreateTrainingPlanDTO newTrainingPlanDto){
        TrainingPlan newTrainingPlan = createPlanDtoToTrainingPlanMapper.apply(newTrainingPlanDto);
        trainingPlanApplication.addTrainingPlan(newTrainingPlan);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @PutMapping
    public ResponseEntity<?> updateTrainingPlan(@RequestBody TrainingPlan updatedTrainingPlan){
        trainingPlanApplication.addTrainingPlan(updatedTrainingPlan);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<?> getTrainingPlans(){
        List<TrainingPlanResource> plans = trainingPlanApplication.getTrainingPlans().stream().map(trainingPlanToPlanResourceMapper).collect(Collectors.toList());
        return ResponseEntity.ok(plans);
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
