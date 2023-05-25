package de.dhbw.cleanproject.adapter.resource;


import de.dhbw.cleanproject.adapter.bodydata.RightLeftDTO;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@NoArgsConstructor
public class BodyDataResource extends HashMap<String, Object> {

    //private final List<List<Object>> hip, breast, shoulders, waist, weight;

    //private final RightLeftResource bicep, forearm, leg;

}


