package ru.astepanov.authservice.controller

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.runners.MockitoJUnitRunner
import org.springframework.context.MessageSource
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.validation.BindingResult
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException

import static org.mockito.Mockito.*
import static org.springframework.context.i18n.LocaleContextHolder.locale

/**
 * @author Artemiy Stepanov (artem.omsk@gmail.com)
 * @version 1.0 (24.04.16)
 */
@RunWith(MockitoJUnitRunner)
class ControllerValidationHandlerTest {

    def ControllerValidationHandler handler
    @Mock
    def MessageSource msgSource

    @Before
    public void setUp() throws Exception {
        handler = new ControllerValidationHandler(messageSource: msgSource)
    }

    @Test
    void "should return error response in case of validation error"() {
        def ex = mock(MethodArgumentNotValidException)
        prepareValidationException(ex)

        def errorResponse = handler.processValidationError(ex)
        assert errorResponse != null
    }

    @Test
    void "should return error response with code from validation error"() {
        def ex = mock(MethodArgumentNotValidException)
        def fieldError = prepareValidationException(ex)
        when(fieldError.getDefaultMessage()).thenReturn("ERROR_CODE")

        def errorResponse = handler.processValidationError(ex)
        assert errorResponse.code == "ERROR_CODE"
    }

    @Test
    void "should correctly call MessageSource with validation error code for error message"() {
        def ex = mock(MethodArgumentNotValidException)
        def fieldError = prepareValidationException(ex)
        when(fieldError.getDefaultMessage()).thenReturn("ERROR_CODE")

        handler.processValidationError(ex)
        verify(msgSource).getMessage("validation.error.ERROR_CODE", null, getLocale())
    }

    @Test
    void "should return validation error response with message from messageSource"() {
        def ex = mock(MethodArgumentNotValidException)
        def fieldError = prepareValidationException(ex)
        when(fieldError.getDefaultMessage()).thenReturn("ERROR_CODE")
        when(msgSource.getMessage("validation.error.ERROR_CODE", null, getLocale()))
                .thenReturn("Validation error message")

        def errorResponse = handler.processValidationError(ex)
        assert errorResponse.message == "Validation error message"
    }

    @Test
    void "should return correct error message in case of AuthProvider creation error"() {
        def ex = mock(HttpMessageNotReadableException)
        when(ex.getMessage()).thenReturn("Message: Unknown value for AuthProvider;")
        when(msgSource.getMessage("validation.error.INVALID_PROVIDER", null, getLocale()))
                .thenReturn("AuthProvider creation error message")

        def errorResponse = handler.processConvertingError(ex)
        verify(msgSource).getMessage("validation.error.INVALID_PROVIDER", null, getLocale())
        assert errorResponse.code == "INVALID_PROVIDER"
        assert errorResponse.message == "AuthProvider creation error message"
    }

    @Test
    void "should return error response with exception's message"() {
        def ex = mock(HttpMessageNotReadableException)
        when(ex.getMessage()).thenReturn("Some error description")

        def errorResponse = handler.processConvertingError(ex)
        assert errorResponse.code == "INVALID_REQUEST"
        assert errorResponse.message == "Some error description"
    }

    def FieldError prepareValidationException(mockedException) {
        def BindingResult bindingResult = mock(BindingResult)
        def fieldError = mock(FieldError)
        when(mockedException.getBindingResult()).thenReturn(bindingResult)
        when(bindingResult.getFieldError()).thenReturn(fieldError)
        return fieldError
    }
}
