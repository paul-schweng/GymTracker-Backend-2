package de.dhbw.cleanproject.domain.bodydata;

import java.util.Map;

public interface BodyDataApplication {

    void addMeasurement(BodyData bodyDataDTO);

    BodyData getBodyData();

}
