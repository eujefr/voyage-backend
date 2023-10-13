package com.backend.voyage.controller;

import static java.util.Objects.nonNull;

import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.voyage.model.User;
import com.backend.voyage.service.Auth.AppTokenProvider;
import com.backend.voyage.service.Auth.GoogleTokenVerify;
import com.backend.voyage.service.UserService;

import lombok.RequiredArgsConstructor;

@RequestMapping("user")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AppTokenProvider appTokenProvider;
    private final GoogleTokenVerify googleTokenVerify;


    @PostMapping("/auth/token")
    public String auth(@RequestBody String body) {

        User user = userService.save(User.fromPayload(googleTokenVerify.verify(User.getToken(body))));

        if (nonNull(user)) {

            return appTokenProvider.generateToken(user.getDsUser());
        }

        return null;
    }

    @GetMapping("/userEmail")
    public User userEmail(@RequestParam String dsEmailUser) {

        return userService.findBydsEmailUsuario(dsEmailUser);
    }
}
