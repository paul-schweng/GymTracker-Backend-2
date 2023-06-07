package de.dhbw.cleanproject.adapter.bodydata.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BodyDataDTO {
    @NotNull
    private RightLeftDTO bicep, forearm, leg;

    @Size(min = 1, max = 1)
    private List<Double> hip, breast, shoulders, waist, weight;

    // getters and setters
}
