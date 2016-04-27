package ru.astepanov.authservice.model;

/**
 * @author Artemiy Stepanov (artem.omsk@gmail.com)
 * @version 1.0 (24.04.16)
 */
public interface Validation {

    /** Min length of SHA256 from password */
    int MIN_PASS_LENGTH = 64;
    /** Max length of SHA256 from password */
    int MAX_PASS_LENGTH = 64;

}
