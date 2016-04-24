package ru.astepanov.authservice.model.register;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.astepanov.authservice.model.AuthProvider;

/**
 * @author Artemiy Stepanov (artem.omsk@gmail.com)
 * @version 1.0 (24.04.16)
 */
@Getter @Setter
@ToString
public class RegisterRequest {

    private String login;
    private String password;
    private AuthProvider provider;

}
