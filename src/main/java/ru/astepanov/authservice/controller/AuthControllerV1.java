package ru.astepanov.authservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.astepanov.authservice.model.login.LoginRequest;
import ru.astepanov.authservice.model.login.LoginResponse;
import ru.astepanov.authservice.model.register.RegisterRequest;
import ru.astepanov.authservice.model.register.RegisterResponse;
import ru.astepanov.authservice.model.register.RegisterResult;
import ru.astepanov.authservice.service.TokenService;

import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author Artemiy Stepanov (artem.omsk@gmail.com)
 * @version 1.0 (24.04.16)
 */
@RestController
@RequestMapping("authservice/ext/v1/")
@Slf4j
public class AuthControllerV1 {

    @Autowired
    private TokenService tokenService;

    @RequestMapping(method = POST, path = "register")
    public RegisterResponse register(@Valid @RequestBody RegisterRequest request) {
        log.info("Attempt to register account with data: {}", request);

        if (request.getPassword() != null) {
            return new RegisterResponse(RegisterResult.CREATED);
        } else {
            return new RegisterResponse(RegisterResult.ALREADY_EXISTS);
        }
    }

    @RequestMapping(method = POST, path = "login")
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {
        log.info("Attempt to login into account with data: {}", request);

        final String token = tokenService.generateToken(request.getLogin(), request.getClientId(), "sessionId");
        log.info("Creating token = {}", tokenService.decryptToken(token));
        return new LoginResponse(token);
    }

}
