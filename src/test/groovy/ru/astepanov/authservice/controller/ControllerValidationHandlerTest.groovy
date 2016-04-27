package ru.astepanov.authservice.controller

import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.runners.MockitoJUnitRunner
import org.springframework.context.MessageSource
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.validation.BindingResult
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException

import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when

/**
 * @author Artemiy Stepanov (artem.omsk@gmail.com)
 * @version 1.0 (24.04.16)
 */
@RunWith(MockitoJUnitRunner)
class ControllerValidationHandlerTest {

    def ControllerValidationHandler handler
    @Mock
    def MessageSource messageSource

    @Before
    public void setUp() throws Exception {
        handler = new ControllerValidationHandler()
        handler.messageSource = messageSource
    }

    @Test
    @Ignore //TODO: rewrite
    void "should return error response with validation error"() {
        def ex = mock(MethodArgumentNotValidException)
        def BindingResult bindingResult = mock(BindingResult)
        def fieldError = mock(FieldError)
        when(ex.getBindingResult()).thenReturn(bindingResult)
        when(bindingResult.getFieldError()).thenReturn(fieldError)
        when(fieldError.getDefaultMessage()).thenReturn("CODE|Error message")

        def errorResponse = handler.processValidationError(ex)
        assert errorResponse.code == "CODE"
        assert errorResponse.message == "Error message"
    }

    @Test
    @Ignore //TODO: rewrite
    void "should return correct error message in case of AuthProvider creation error"() {
        def ex = mock(HttpMessageNotReadableException)
        when(ex.getMessage()).thenReturn("Message: Unknown value for AuthProvider;")

        def errorResponse = handler.processConvertingError(ex)
        assert errorResponse.code == "INVALID_PROVIDER"
        assert errorResponse.message == "Некорректное значение параметра provider"
    }

    @Test
    void "should return error response with exception's message"() {
        def ex = mock(HttpMessageNotReadableException)
        when(ex.getMessage()).thenReturn("Some error description")

        def errorResponse = handler.processConvertingError(ex)
        assert errorResponse.code == "INVALID_REQUEST"
        assert errorResponse.message == "Some error description"
    }
}
