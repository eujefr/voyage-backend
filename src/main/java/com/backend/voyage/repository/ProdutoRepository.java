package com.backend.voyage.repository;

import com.backend.voyage.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    List<Produto> findByIdPostIn(Iterable<Integer> ids);
}
