package ru.astepanov.authservice.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;
import ru.astepanov.authservice.model.Token;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author Artemiy Stepanov (artem.omsk@gmail.com)
 * @version 1.0 (29.04.16)
 */
@Service
public class TokenService {

    public static final String CLIENT_ID = "clientId";
    public static final String SESSION_ID = "sessionId";

    //TODO: move to props
    private String issuer = "issuer";
    //TODO: move to props (??)
    private String secret = "secretKey";

    public String generateToken(final String login, final String clientId, final String sessionId) {
        return Jwts.builder()
            .setSubject(login)
            .claim(CLIENT_ID, clientId)
            .claim(SESSION_ID, sessionId)
            .setIssuer(issuer)
            .setIssuedAt(new Date())
            .signWith(SignatureAlgorithm.HS512, secret)
            .compact();
    }

    public Token decryptToken(String tokenStr) {
        final Claims claims = Jwts.parser()
            .setSigningKey(secret)
            .requireIssuer(issuer)
            .parseClaimsJws(tokenStr).getBody();

        final Token token = new Token();
        token.setIssuer(claims.getIssuer());
        token.setIssuedAt(LocalDateTime.ofInstant(
            Instant.ofEpochMilli(claims.getIssuedAt().getTime()),
            ZoneId.systemDefault()
        ));
        token.setLogin(claims.getSubject());
        token.setClientId(claims.get(CLIENT_ID, String.class));
        token.setSessionId(claims.get(SESSION_ID, String.class));
        return token;
    }

}
