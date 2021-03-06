package ru.astepanov.authservice.model.login

import org.hibernate.validator.constraints.Email
import org.hibernate.validator.constraints.NotBlank
import org.junit.Test

import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

import static ru.astepanov.authservice.TestHelper.findFieldAnnotation

/**
 * @author Artemiy Stepanov (artem.omsk@gmail.com)
 * @version 1.0 (29.04.16)
 */
class LoginRequestTest {

    @Test
    void "login field should be annotated with @NotBlank"() {
        def ann = findFieldAnnotation(LoginRequest, "login", NotBlank)
        assert ann != null
    }

    @Test
    void "login field's @NotBlank should be specified with correct error code"() {
        def ann = findFieldAnnotation(LoginRequest, "login", NotBlank)
        assert ann.message() == "LOGIN_IS_EMPTY"
    }

    @Test
    void "login field should be annotated with @Email"() {
        def ann = findFieldAnnotation(LoginRequest, "login", Email)
        assert ann != null
    }

    @Test
    void "login field's @Email should be specified with correct error code"() {
        def ann = findFieldAnnotation(LoginRequest, "login", Email)
        assert ann.message() == "INVALID_LOGIN"
    }

    @Test
    void "password field should be annotated with @NotBlank"() {
        def ann = findFieldAnnotation(LoginRequest, "password", NotBlank)
        assert ann != null
    }

    @Test
    void "password field's @NotBlank should be specified with correct error code"() {
        def ann = findFieldAnnotation(LoginRequest, "password", NotBlank)
        assert ann.message() == "PASSWORD_IS_EMPTY"
    }

    @Test
    void "password field should be annotated with @Size"() {
        def ann = findFieldAnnotation(LoginRequest, "password", Size)
        assert ann != null
    }

    @Test
    void "password field's @Size should be specified with correct error code"() {
        def ann = findFieldAnnotation(LoginRequest, "password", Size)
        assert ann.message() == "INVALID_PASSWORD"
    }

    @Test
    void "password field's @Size should be specified with correct min size"() {
        def ann = findFieldAnnotation(LoginRequest, "password", Size)
        assert ann.min() == 64
    }

    @Test
    void "password field's @Size should be specified with correct max size"() {
        def ann = findFieldAnnotation(LoginRequest, "password", Size)
        assert ann.max() == 64
    }

    @Test
    void "clientId field should be annotated with @NotBlank"() {
        def ann = findFieldAnnotation(LoginRequest, "clientId", NotBlank)
        assert ann != null
    }

    @Test
    void "clientId field's @NotBlank should be specified with correct error code"() {
        def ann = findFieldAnnotation(LoginRequest, "clientId", NotBlank)
        assert ann.message() == "CLIENTID_IS_EMPTY"
    }

    @Test
    void "provider field should be annotated with @NotNull"() {
        def ann = findFieldAnnotation(LoginRequest, "provider", NotNull)
        assert ann != null
    }

    @Test
    void "provider field's @NotNull should be specified with correct error code"() {
        def ann = findFieldAnnotation(LoginRequest, "provider", NotNull)
        assert ann.message() == "PROVIDER_ID_EMPTY"
    }
}
