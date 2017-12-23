package com.example.demo;

import com.auth0.jwk.JwkException;
import com.auth0.jwk.JwkProvider;
import com.auth0.jwk.UrlJwkProvider;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

public class RSAKeyProvider implements com.auth0.jwt.interfaces.RSAKeyProvider {

    private final JwkProvider _provider;

    public RSAKeyProvider(String domain) throws MalformedURLException {
        URL url = new URL(domain);
        URL fullUrl = new URL(url, "/.well-known/openid-configuration/jwks");
        _provider = new UrlJwkProvider(fullUrl);
    }

    @Override
    public RSAPublicKey getPublicKeyById(String s) {
        try {
            return (RSAPublicKey) _provider.get(s).getPublicKey();
        } catch (JwkException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public RSAPrivateKey getPrivateKey() {
        return null;
    }

    @Override
    public String getPrivateKeyId() {
        return null;
    }
}
