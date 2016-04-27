package ru.astepanov.authservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
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

import java.util.Locale;

/**
 * @author Artemiy Stepanov (artem.omsk@gmail.com)
 * @version 1.0 (24.04.16)
 */
@ControllerAdvice
public class ControllerValidationHandler {

    private static final String VALIDATION_ERROR_PREFIX = "validation.error.";

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse processValidationError(final MethodArgumentNotValidException ex) {
        final BindingResult result = ex.getBindingResult();
        final FieldError error = result.getFieldError();

        final String errorCode = error.getDefaultMessage();
        return new ErrorResponse(errorCode, getMessageByCode(errorCode));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse processConvertingError(final HttpMessageNotReadableException ex) {
        final String result = ex.getMessage();
        if (result != null && result.contains("Unknown value for AuthProvider")) {
            final String errorCode = "INVALID_PROVIDER";
            return new ErrorResponse(errorCode, getMessageByCode(errorCode));
        } else {
            return new ErrorResponse("INVALID_REQUEST", result);
        }
    }

    private String getMessageByCode(final String errorCode) {
        final Locale currentLocale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(VALIDATION_ERROR_PREFIX + errorCode, null, currentLocale);
    }

}
