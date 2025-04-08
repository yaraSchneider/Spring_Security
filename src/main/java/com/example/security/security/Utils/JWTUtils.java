package com.example.security.security.Utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public  class JWTUtils {

    private final String SENHAJWT = "SenhaSegur4!";

    public String gerarToken(UserDetails user){
        Algorithm algorithm = Algorithm.HMAC256(SENHAJWT);
        return JWT.create().withIssuer("") // Quem é a entidade assinante do token ex: SENAI
                .withIssuedAt(criacao()) // define o momento de criacao
                .withExpiresAt(validade())// define a validade do token
                .withSubject(user.getUsername()) // define a indentidade do usuario
                .sign(algorithm); //assinatura
    }

    private Instant criacao(){
        return ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")).toInstant();
    }

    private Instant validade(){
        return ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")).plusMinutes(30).toInstant();
    }


    public void validarToken(String token){
        Algorithm algorithm = Algorithm.HMAC256(SENHAJWT);
        DecodedJWT decodedJWT = JWT.decode(token);
        algorithm.verify(decodedJWT);
        if (decodedJWT.getExpiresAt().toInstant().isBefore(criacao())){
            throw new TokenExpiredException("", decodedJWT.getExpiresAt().toInstant());
        }
    }

    public String getUsername(String token) {
        return JWT.decode(token).getSubject();
    }
}
