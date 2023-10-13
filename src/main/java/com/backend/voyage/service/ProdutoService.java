package com.backend.voyage.service;

import com.backend.voyage.model.Produto;
import com.backend.voyage.repository.ProdutoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public List<Produto> findByIdPostIn(List<Integer> idProduto) {

        return produtoRepository.findByIdPostIn(idProduto);
    }

    public List<Produto> saveMany(List<Produto> produtoList) {

        return produtoRepository.saveAll(produtoList);
    }
}
