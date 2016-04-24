package ru.astepanov.authservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.astepanov.authservice.model.ErrorResponse;

/**
 * @author Artemiy Stepanov (artem.omsk@gmail.com)
 * @version 1.0 (24.04.16)
 */
@ControllerAdvice
public class ControllerValidationHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse processValidationError(final MethodArgumentNotValidException ex) {
        final BindingResult result = ex.getBindingResult();
        final FieldError error = result.getFieldError();

        return ErrorResponse.create(error.getDefaultMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse processConvertingError(final HttpMessageNotReadableException ex) {
        final String result = ex.getMessage();
        if (result != null && result.contains("Unknown value for AuthProvider"))
            return new ErrorResponse("INVALID_PROVIDER", "Некорректное значение параметра provider");
        else
            return new ErrorResponse("INVALID_REQUEST", result);
    }

}
