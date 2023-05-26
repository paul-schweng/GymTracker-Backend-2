package de.dhbw.cleanproject.adapter.bodydata.resource;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public
class RightLeftResource {
    private final List<List<Object>> right, left;
}
