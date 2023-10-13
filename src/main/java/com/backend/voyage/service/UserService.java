package com.backend.voyage.service;

import static java.util.Objects.nonNull;

import java.util.List;

import org.springframework.stereotype.Service;

import com.backend.voyage.model.User;
import com.backend.voyage.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User save(User userParam) {

        User user;

        user = userRepository.findBydsEmailUsuario(userParam.getDsEmailUsuario());

        if (!nonNull(user)) {

            user = userRepository.save(userParam);
        }

       return user;
    }

    public User findBydsEmailUsuario(String dsEmailUser) {

        return userRepository.findBydsEmailUsuario(dsEmailUser);
    }

    public List<User> findAllByidUserIn(List<Integer> idUser) {

        return userRepository.findAllByidUserIn(idUser);
    }
}
