package com.backend.voyage.service;

import static java.util.Objects.nonNull;
import static org.hibernate.internal.util.collections.CollectionHelper.isNotEmpty;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.backend.voyage.model.Produto;
import com.backend.voyage.model.ProdutoReserva;
import com.backend.voyage.repository.ProdutoRepository;
import com.backend.voyage.repository.ProdutoReservaRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProdutoReservaService {

    private final ProdutoReservaRepository produtoReservaRepository;
    private final ProdutoRepository produtoRepository;

    public List<ProdutoReserva> saveMany(String body) {

        return produtoReservaRepository.saveAll(ProdutoReserva.getProdutosJson(body));
    }

    public  List<BigDecimal> calculoReservas(Integer idPost) {

        BigDecimal vlTotal = new BigDecimal("0.00");
        Integer totalProdutosReservados = 0;

        List<ProdutoReserva> produtoReservas = produtoReservaRepository.findAllByIdPost(idPost);

        if (produtoReservas != null && produtoReservas.size() > 0) {

            List<Produto> produtos = produtoRepository.findByIdProdutoIn(
                    produtoReservas.stream().map(ProdutoReserva::getIdProduto).collect(Collectors.toList()));

            totalProdutosReservados = produtoReservas.stream().map(produtoReserva ->
                    Integer.parseInt(produtoReserva.getQtdProduto())).reduce(0, Integer::sum);

            for (ProdutoReserva produtoReserva : produtoReservas) {

                BigDecimal vlProduto = new BigDecimal(produtos.stream().filter(produto ->
                        produto.getIdProduto().equals(produtoReserva.getIdProduto())).toList().get(0).getVlProduto());

                vlTotal = vlTotal.add(vlProduto.multiply(new BigDecimal(produtoReserva.getQtdProduto())));
            }
        }

        return Arrays.asList(new BigDecimal(totalProdutosReservados), vlTotal);
    }

    public  List<ProdutoReserva> findAllByIdPostAndIdUser(Integer idPost, Integer idUser) {

        return produtoReservaRepository.findAllByIdPostAndIdUser(idPost, idUser);
    }
}
