package com.backend.voyage.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

@Entity
@Getter
@Setter
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_produto")
    private Integer idProduto;

    @JoinColumn(name = "id_post", referencedColumnName = "id_post")
    private Integer idPost;

    @Column(name = "ds_produto")
    private String dsProduto;

    @Column(name = "vl_produto")
    private String vlProduto;

    public Produto() {
    }

    public Produto(Integer idPost) {
        this.idPost = idPost;
    }

    public static List<Produto> fromJson(String json) {

        List<Produto> list = new ArrayList<>();

        if (nonNull(json)) {

            try {

                JSONArray jsonArray = new JSONObject(json).getJSONArray("produto");

                for (int i = 0; i < jsonArray.length(); i++) {

                    Produto produto = new Produto();
                    produto.setDsProduto(jsonArray.getJSONObject(i).get("dsProduto").toString());
                    produto.setVlProduto(jsonArray.getJSONObject(i).get("vlProduto").toString());

                    list.add(produto);
                }

            } catch (Exception e) {

                System.out.println("o valor não foi encontrado no json");
            }
        }

        return list;
    }

}
