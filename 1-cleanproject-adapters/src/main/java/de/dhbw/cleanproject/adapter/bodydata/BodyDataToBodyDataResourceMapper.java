package de.dhbw.cleanproject.adapter.bodydata;

import de.dhbw.cleanproject.adapter.bodydata.RightLeftDTO;
import de.dhbw.cleanproject.adapter.bodydata.RightLeftResource;
import de.dhbw.cleanproject.domain.bodydata.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Component
public class BodyDataToBodyDataResourceMapper implements Function<BodyData, RightLeftDTO.BodyDataResource> {
    @Override
    public RightLeftDTO.BodyDataResource apply(BodyData bodyData) {
        if(bodyData == null)
            return null;

        return map(bodyData);

    }

    private RightLeftDTO.BodyDataResource map(BodyData bodyData) {
        RightLeftDTO.BodyDataResource bodyParts = new RightLeftDTO.BodyDataResource();

        for (BodyPartType type : BodyPartType.values()) {
            String key = type.name().toLowerCase();

            if(type.isDual()){
                List<BodyMeasurement> right = bodyData.getBodyParts().stream()
                        .filter(part -> part.getType().equals(type) && part.getSide().equals(BodySide.RIGHT))
                        .findFirst().get().getMeasurements();

                List<BodyMeasurement> left = bodyData.getBodyParts().stream()
                        .filter(part -> part.getType().equals(type) && part.getSide().equals(BodySide.LEFT))
                        .findFirst().get().getMeasurements();

                bodyParts.put(key, RightLeftResource.builder()
                        .right(mapMeasurementToObjectList(right))
                        .left(mapMeasurementToObjectList(left))
                        .build());
            }else {
                List<BodyMeasurement> measurements = bodyData.getBodyParts().stream()
                        .filter(part -> part.getType().equals(type) && part.getSide().equals(BodySide.NONE))
                        .findFirst().get().getMeasurements();

                bodyParts.put(key, mapMeasurementToObjectList(measurements));
            }

        }
        return bodyParts;
    }


    private List<List<Object>> mapMeasurementToObjectList(List<BodyMeasurement> measurements){
        List<List<Object>> objectList = new ArrayList<>();

        measurements.forEach(m ->
                objectList.add(List.of(m.getDate(), m.getValue()))
        );
        return objectList;
    }


}
