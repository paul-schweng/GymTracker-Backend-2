package de.dhbw.cleanproject.adapter.bodydata.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;


@Data
@Builder
public class BodyDataDTO {
    @NotNull
    private RightLeftDTO bicep, forearm, leg;

    @Size(min = 1, max = 1)
    private List<Double> hip, breast, shoulders, waist, weight;

    // getters and setters
}
