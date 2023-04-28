package de.dhbw.plugins.rest.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErrorResponse {

    private String message;
    private String title;
}
