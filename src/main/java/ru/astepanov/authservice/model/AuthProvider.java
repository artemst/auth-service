package ru.astepanov.authservice.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author Artemiy Stepanov (artem.omsk@gmail.com)
 * @version 1.0 (24.04.16)
 */
public enum AuthProvider {

    /** Authentication via email */
    EMAIL("email");

    private String value;

    AuthProvider(final String value) {
        this.value = value;
    }

    @JsonValue
    final String value() {
        return this.value;
    }

    @JsonCreator
    public static AuthProvider fromValue(String value) {
        if (EMAIL.value.equalsIgnoreCase(value))
            return EMAIL;

        throw new IllegalArgumentException("Unknown value for AuthProvider: '" + value + "'");
    }
}


