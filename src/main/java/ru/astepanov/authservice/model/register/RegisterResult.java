package ru.astepanov.authservice.model.register;

/**
 * @author Artemiy Stepanov (artem.omsk@gmail.com)
 * @version 1.0 (24.04.16)
 */
public enum RegisterResult {

    /** New account has been created */
    CREATED,
    /** Account with the same login already exists */
    ALREADY_EXISTS

}
