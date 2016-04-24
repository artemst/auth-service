package ru.astepanov.authservice.model.register;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import ru.astepanov.authservice.model.AuthProvider;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static ru.astepanov.authservice.model.Validation.MAX_PASSWORD_LENGTH;
import static ru.astepanov.authservice.model.Validation.MIN_PASSWORD_LENGTH;

/**
 * @author Artemiy Stepanov (artem.omsk@gmail.com)
 * @version 1.0 (24.04.16)
 */
@Getter @Setter
@ToString
public class RegisterRequest {

    /** В качестве логина передается email пользователя */
    @NotBlank(message = "INVALID_LOGIN|Некорректное значения параметра login")
    @Email(message = "INVALID_LOGIN|Некорректное значения параметра login")
    private String login;

    /** Передается хэш SHA256 от пользовательского пароля */
    @NotBlank(message = "INVALID_PASSWORD|Некорректное значение параметра password")
    @Size(min = MIN_PASSWORD_LENGTH, max = MAX_PASSWORD_LENGTH,
        message = "INVALID_PASSWORD|Некорректное значение параметра password")
    private String password;

    @NotNull(message = "INVALID_PROVIDER|Некорректное значение параметра provider")
    private AuthProvider provider;

}
