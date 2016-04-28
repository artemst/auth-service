package ru.astepanov.authservice.model.register;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import ru.astepanov.authservice.model.AuthProvider;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static ru.astepanov.authservice.model.Validation.MAX_PASS_LENGTH;
import static ru.astepanov.authservice.model.Validation.MIN_PASS_LENGTH;

/**
 * @author Artemiy Stepanov (artem.omsk@gmail.com)
 * @version 1.0 (24.04.16)
 */
@Getter @Setter
@ToString
public class RegisterRequest {

    /** В качестве логина передается email пользователя */
    @NotBlank(message = "LOGIN_IS_EMPTY")
    @Email(message = "INVALID_LOGIN")
    private String login;

    /** Передается хэш SHA256 от пользовательского пароля */
    @NotBlank(message = "PASSWORD_IS_EMPTY")
    @Size(min = MIN_PASS_LENGTH, max = MAX_PASS_LENGTH, message = "INVALID_PASSWORD")
    private String password;

    @NotNull(message = "PROVIDER_ID_EMPTY")
    private AuthProvider provider;

}
