package de.dhbw.plugins.rest;

import de.dhbw.cleanproject.adapter.trainingplan.*;
import de.dhbw.cleanproject.adapter.trainingplan.dto.CreateTrainingPlanDTO;
import de.dhbw.cleanproject.adapter.trainingplan.dto.UpdateTrainingPlanDTO;
import de.dhbw.cleanproject.adapter.trainingplan.resource.TrainingPlanResource;
import de.dhbw.cleanproject.domain.trainingplan.TrainingPlan;
import de.dhbw.cleanproject.domain.trainingplan.TrainingPlanApplication;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/training")
@RequiredArgsConstructor
public class TrainingPlanController {

    private final TrainingPlanApplication trainingPlanApplication;
    private final CreatePlanDtoToTrainingPlanMapper createPlanDtoToTrainingPlanMapper;
    private final TrainingPlanToPlanResourceMapper trainingPlanToPlanResourceMapper;
    private final UpdatePlanDtoToTrainingPlanMapper updateTrainingPlanDtoToTrainingPlanMapper;

    @PostMapping
    public ResponseEntity<?> addTrainingPlan(@RequestBody CreateTrainingPlanDTO newTrainingPlanDto){
        TrainingPlan newTrainingPlan = createPlanDtoToTrainingPlanMapper.apply(newTrainingPlanDto);
        trainingPlanApplication.addTrainingPlan(newTrainingPlan);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @PutMapping
    public ResponseEntity<?> updateTrainingPlan(@RequestBody UpdateTrainingPlanDTO updatedTrainingPlanDto){
        TrainingPlan updatedTrainingPlan = updateTrainingPlanDtoToTrainingPlanMapper.apply(updatedTrainingPlanDto);
        trainingPlanApplication.updateTrainingPlan(updatedTrainingPlan);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<?> getTrainingPlans(){
        List<TrainingPlanResource> plans = trainingPlanApplication.getTrainingPlans().stream().map(trainingPlanToPlanResourceMapper).collect(Collectors.toList());
        return ResponseEntity.ok(plans);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTrainingPlan(@PathVariable("id") UUID id){
        trainingPlanApplication.deleteTrainingPlan(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
