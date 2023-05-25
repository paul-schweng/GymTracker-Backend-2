package de.dhbw.cleanproject.adapter.bodydata;

import lombok.Data;

import javax.validation.constraints.Size;
import java.util.List;

@Data
public class RightLeftDTO {

    @Size(min = 1, max = 1)
    private List<Double> right, left;

}
