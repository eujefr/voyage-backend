package com.backend.voyage.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.voyage.model.ProdutoReserva;

public interface ProdutoReservaRepository extends JpaRepository<ProdutoReserva, Integer> {

    List<ProdutoReserva> findAllByIdPostAndIdUser(Integer idPost, Integer idUser);
    List<ProdutoReserva> findAllByIdPost(Integer idPost);
}
