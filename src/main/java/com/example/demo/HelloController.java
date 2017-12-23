package com.example.demo;

import com.auth0.jwk.Jwk;
import com.auth0.jwk.JwkException;
import com.auth0.jwk.JwkProvider;
import com.auth0.jwk.UrlJwkProvider;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.sun.org.apache.xml.internal.security.algorithms.JCEMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;

@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String index(String token) throws JwkException, MalformedURLException {
        if (token == null) {
            token = "eyJhbGciOiJSUzI1NiIsImtpZCI6ImNiOWYzNzI2MWQxYmUwYzg1OTRlYjAwZGE5MDEyZGU0IiwidHlwIjoiSldUIn0.eyJuYmYiOjE1MTQwMjc4NDAsImV4cCI6MTUxNDAzMTQ0MCwiaXNzIjoiaHR0cDovLzE3Mi4yNy4xOTYuMTQ6ODEwMCIsImF1ZCI6WyJodHRwOi8vMTcyLjI3LjE5Ni4xNDo4MTAwL3Jlc291cmNlcyIsImFwaTEiXSwiY2xpZW50X2lkIjoianMiLCJzdWIiOiI4MTg3MjciLCJhdXRoX3RpbWUiOjE1MTM5OTM1NzksImlkcCI6ImxvY2FsIiwic2NvcGUiOlsib3BlbmlkIiwicHJvZmlsZSIsImFwaTEiXSwiYW1yIjpbInB3ZCJdfQ.1fJU0qr4S_Z19gLpfb1-6gHEhZ49Y05jtLuxVRrx5RNmnv85xN58djOHcZnAHTVKGRpcv3Y9J7eW_u1qkzhNcY7x62QiZSJKnD6CZAjoXS4KPsBWZLXjxpm8aXvE2HAJ27mnOW2juY1yU6ZSIizPDPQAHV0EAqWnvXbt305eLjEgp2-PNXlO95alwTAJ945trs1mOGJBlUnLkmXuGEouFegkFomEgZleyWdagrL9E8PPVGRGDVgwu7OL4QcJdeOPgulB2enrfVs5bbCxPvXuhLGBC2Cd8LYU0ZhBQVjvuGTMYikA8nIIEzEZsovhjXemoG9YFETJKm26hK3vF1ma7Q";
        }
        String message = "success";
        RSAKeyProvider keyProvider = new RSAKeyProvider("http://172.27.196.14:8100/");
        try {
            Algorithm algorithm = Algorithm.RSA256(keyProvider);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("http://172.27.196.14:8100")
                    .build();
            verifier.verify(token);
        } catch (JWTVerificationException exception){
            message = exception.getMessage();
        }
        return message;
    }
}
