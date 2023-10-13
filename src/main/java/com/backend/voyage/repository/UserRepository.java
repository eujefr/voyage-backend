package com.backend.voyage.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.voyage.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findBydsEmailUsuario(String email);
    List<User> findAllByidUserIn(List<Integer> idUser);
}
