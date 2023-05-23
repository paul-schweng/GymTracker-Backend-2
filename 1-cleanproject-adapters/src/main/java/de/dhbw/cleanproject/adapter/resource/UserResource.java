package de.dhbw.cleanproject.adapter.resource;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class UserResource {
    private final UUID id;
    private final String  username, name;
}
