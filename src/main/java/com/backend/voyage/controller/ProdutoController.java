package com.backend.voyage.controller;

import com.backend.voyage.model.Produto;
import com.backend.voyage.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("produto")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;

    @GetMapping("/{idPost}")
    private ResponseEntity<List<Produto>> getProdutoByIdPost(@PathVariable Integer idPost) {

        return ResponseEntity.ok(produtoService.findByIdPostIn(Collections.singletonList(idPost)));
    }
}
