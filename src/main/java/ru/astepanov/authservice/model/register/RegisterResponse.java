package ru.astepanov.authservice.model.register;

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
public class RegisterResponse {

    private RegisterResult result;

}
