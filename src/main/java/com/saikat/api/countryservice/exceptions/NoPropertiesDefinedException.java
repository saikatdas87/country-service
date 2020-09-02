package com.saikat.api.countryservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoPropertiesDefinedException extends Exception {
    public NoPropertiesDefinedException(String message) {
        super(message);
    }
}
