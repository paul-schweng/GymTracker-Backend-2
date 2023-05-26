package de.dhbw.cleanproject.adapter.bodydata.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.util.HashMap;
import java.util.List;

@Data
public class RightLeftDTO {

    @Size(min = 1, max = 1)
    private List<Double> right, left;

    @NoArgsConstructor
    public static class BodyDataResource extends HashMap<String, Object> {

        //private final List<List<Object>> hip, breast, shoulders, waist, weight;

        //private final RightLeftResource bicep, forearm, leg;

    }
}
