package com.example.springsecurityjwttutorial.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component // Marks this as a component. Now, Spring Boot will handle the creation and management of the JWTUtil Bean
// and you will be able to inject it in other places of your code
public class JWTUtil {

    // Injects the jwt-secret property set in the resources/application.properties file
    @Value("${jwt-secret}")
    private String secret;

    // Method to sign and create a JWT using the injected secret
    public String generateToken(String email) throws IllegalArgumentException, JWTCreationException {
        return JWT.create()
                .withSubject("User Details")
                .withClaim("email", email)
                .withIssuedAt(new Date())
                .withIssuer("YOUR APPLICATION/PROJECT/COMPANY NAME")
                .sign(Algorithm.HMAC256(secret));
    }

    // Method to verify the JWT and then decode and extract the user email stored in the payload of the token
    public String validateTokenAndRetrieveSubject(String token)throws JWTVerificationException {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
                .withSubject("User Details")
                .withIssuer("YOUR APPLICATION/PROJECT/COMPANY NAME")
                .build();
        DecodedJWT jwt = verifier.verify(token);
        return jwt.getClaim("email").asString();
    }

}
