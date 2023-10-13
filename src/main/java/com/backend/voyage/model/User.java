package com.backend.voyage.model;

import static java.util.Objects.nonNull;

import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_user")
    private Integer idUser;

    @Column(name = "ds_user")
    private String dsUser;

    @Column(name = "ds_email_usuario")
    private String dsEmailUsuario;

    @Column(name = "ds_img_user")
    private String dsImgUser;

    public User() {

    }

    public static String getToken(String userJson) {

        String token = null;

        if (nonNull(userJson)) {

            try {

                JSONObject json = new JSONObject(userJson);
                token = json.get("token").toString();

            } catch (Exception e) {

                System.out.println("o valor não foi encontrado no json");
            }
        }

        return token;
    }

    public static User fromPayload(Payload payload) {

        User user = new User();

        user.setDsUser(payload.get("name").toString());
        user.setDsEmailUsuario(payload.getEmail());
        user.setDsImgUser(payload.get("picture").toString());

        return user;
    }
}
