package com.backend.voyage.service.Auth;

import java.util.Arrays;

import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.stereotype.Component;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;

@Component
public class GoogleTokenVerify {

    private static final String CLIENT_ID_ANDROID
            = "asdjasklsadjlakd";
    private static final String CLIENT_ID_WEB
            = "adasdasdasdsad";

    private static Payload payload;

    public Payload verify(String idTokenString) {

        return verifyToken(idTokenString);
    }

    private static Payload verifyToken(String idTokenString) {

        final GoogleIdTokenVerifier verifier =
                new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new GsonFactory())
                .setAudience(Arrays.asList(CLIENT_ID_WEB, CLIENT_ID_ANDROID))
                .build();

        GoogleIdToken idToken;

        try {

            idToken = verifier.verify(idTokenString);

        } catch (Exception e) {

            throw new InvalidTokenException("idToken is invalid");
        }

        return idToken.getPayload();
    }
}
