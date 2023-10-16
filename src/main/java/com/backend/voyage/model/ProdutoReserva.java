package com.backend.voyage.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ProdutoReserva {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_produto_reserva")
    private Integer idProdutoReserva;

    @JoinColumn(name = "id_post", referencedColumnName = "id_post")
    private Integer idPost;

    @JoinColumn(name = "id_produto", referencedColumnName = "id_produto")
    private Integer idProduto;

    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    private Integer idUser;

    @Column(name = "qtd_produto")
    private String qtdProduto;

    public ProdutoReserva() {

    }

    public ProdutoReserva(Integer idPost, Integer idProduto, Integer idUser, String qtdProduto) {

        this.idPost = idPost;
        this.idProduto = idProduto;
        this.idUser = idUser;
        this.qtdProduto = qtdProduto;
    }

    public static List<ProdutoReserva> getProdutosJson(String json) {

        List<ProdutoReserva> produtoReservaList = new ArrayList<>();

        try {

            JSONArray jsonArray = new JSONArray(json);

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);

                produtoReservaList.add(new ProdutoReserva(Integer.parseInt(jsonObject.get("idPost").toString()),
                        Integer.parseInt(jsonObject.get("idProduto").toString()),
                        Integer.parseInt(jsonObject.get("idUser").toString()),
                        jsonObject.get("qtdProduto").toString()));

            }

        } catch (Exception e) {

            throw new IllegalArgumentException(e);
        }

        return produtoReservaList;
    }
}
