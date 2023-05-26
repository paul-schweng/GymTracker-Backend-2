package de.dhbw.plugins.rest;

import de.dhbw.cleanproject.adapter.bodydata.dto.BodyDataDTO;
import de.dhbw.cleanproject.adapter.bodydata.BodyDataDtoToBodyDataMapper;
import de.dhbw.cleanproject.adapter.bodydata.BodyDataToBodyDataResourceMapper;
import de.dhbw.cleanproject.domain.bodydata.*;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/data")
@RequiredArgsConstructor
public class BodyDataController {

    private final BodyDataApplication bodyDataApplication;
    private final BodyDataDtoToBodyDataMapper bodyDataDtoToBodyDataMapper;
    private final BodyDataToBodyDataResourceMapper bodyDataToBodyDataResourceMapper;

    @PostMapping
    public ResponseEntity<?> addBodyData(@RequestBody BodyDataDTO bodyDataDTO){
        BodyData newBodyData = bodyDataDtoToBodyDataMapper.apply(bodyDataDTO);
        bodyDataApplication.addMeasurement(newBodyData);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<?> getBodyData(){
        BodyData bodyData = bodyDataApplication.getBodyData();

        return new ResponseEntity<>(bodyDataToBodyDataResourceMapper.apply(bodyData), HttpStatus.OK);
    }

}



