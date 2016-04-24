package ru.astepanov.authservice.model;

/**
 * @author Artemiy Stepanov (artem.omsk@gmail.com)
 * @version 1.0 (24.04.16)
 */
public interface Validation {

    /** Min length of SHA256 */
    int MIN_PASSWORD_LENGTH = 64;
    /** Max length of SHA256 */
    int MAX_PASSWORD_LENGTH = 64;

}
