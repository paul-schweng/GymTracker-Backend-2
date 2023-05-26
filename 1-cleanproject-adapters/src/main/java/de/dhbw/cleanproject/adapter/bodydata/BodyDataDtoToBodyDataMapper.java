package de.dhbw.cleanproject.adapter.bodydata;

import de.dhbw.cleanproject.adapter.bodydata.dto.BodyDataDTO;
import de.dhbw.cleanproject.adapter.bodydata.dto.RightLeftDTO;
import de.dhbw.cleanproject.domain.bodydata.BodyData;
import de.dhbw.cleanproject.domain.bodydata.BodyPart;
import de.dhbw.cleanproject.domain.bodydata.BodyPartType;
import de.dhbw.cleanproject.domain.bodydata.BodyMeasurement;
import de.dhbw.cleanproject.domain.bodydata.BodySide;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Component
public class BodyDataDtoToBodyDataMapper implements Function<BodyDataDTO, BodyData> {
    @Override
    public BodyData apply(BodyDataDTO bodyDataDto) {
        try {
            return map(bodyDataDto);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    private BodyData map(BodyDataDTO bodyDataDto) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        return BodyData.builder()
                .bodyParts(getBodyParts(bodyDataDto))
                .build();
    }

    private List<BodyPart> getBodyParts(BodyDataDTO bodyDataDto) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException  {
        List<BodyPart> bodyParts = new ArrayList<>();
        for (BodyPartType bodyPartType : BodyPartType.values()) {


            String getterName = "get" + bodyPartType.name().substring(0,1).toUpperCase() + bodyPartType.name().substring(1).toLowerCase();
            Method m = BodyDataDTO.class.getMethod(getterName);

            Object value = m.invoke(bodyDataDto);

            if(value instanceof List) {
                BodyMeasurement measurement = BodyMeasurement.builder()
                        .date(LocalDate.now().toString())
                        .value((Double) ((List<?>) value).get(0))
                        .build();

                BodyPart bodyPart = BodyPart.builder()
                        .type(bodyPartType)
                        .side(BodySide.NONE)
                        .measurements(List.of(measurement))
                        .build();

                bodyParts.add(bodyPart);

            } else if(value instanceof RightLeftDTO) {
                Double right = ((RightLeftDTO) value).getRight().get(0);
                Double left = ((RightLeftDTO) value).getLeft().get(0);

                BodyMeasurement mRight = BodyMeasurement.builder()
                        .date(LocalDate.now().toString())
                        .value(right)
                        .build();

                BodyMeasurement mLeft = BodyMeasurement.builder()
                        .date(LocalDate.now().toString())
                        .value(left)
                        .build();

                BodyPart bodyPartRight = BodyPart.builder()
                        .type(bodyPartType)
                        .measurements(List.of(mRight))
                        .side(BodySide.RIGHT)
                        .build();

                BodyPart bodyPartLeft = BodyPart.builder()
                        .type(bodyPartType)
                        .measurements(List.of(mLeft))
                        .side(BodySide.LEFT)
                        .build();

                bodyParts.add(bodyPartRight);
                bodyParts.add(bodyPartLeft);

            } else {
                throw new RuntimeException("Unknown type");
            }
        }
        return bodyParts;
    }
}
