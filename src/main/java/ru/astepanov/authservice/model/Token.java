package ru.astepanov.authservice.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @author Artemiy Stepanov (artem.omsk@gmail.com)
 * @version 1.0 (29.04.16)
 */
@Getter @Setter
@ToString
public class Token {

    private String issuer;
    private LocalDateTime issuedAt;

    private String login;
    private String clientId;
    private String sessionId;

}
