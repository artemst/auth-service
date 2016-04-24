package ru.astepanov.authservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Artemiy Stepanov (artem.omsk@gmail.com)
 * @version 1.0 (24.04.16)
 */
@AllArgsConstructor
@Getter @Setter
@ToString
public class ErrorResponse {

    private String code;
    private String message;

    public static ErrorResponse create(String errorMessage) {
        errorMessage = errorMessage != null ? errorMessage : "";
        final String[] fields = errorMessage.split("\\|", 2);
        return new ErrorResponse(fields[0].trim(), fields.length > 1 ? fields[1].trim() : "");
    }

}
