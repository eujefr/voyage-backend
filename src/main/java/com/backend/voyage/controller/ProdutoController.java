package com.backend.voyage.controller;

import com.backend.voyage.model.Produto;
import com.backend.voyage.model.ProdutoReserva;
import com.backend.voyage.service.ProdutoReservaService;
import com.backend.voyage.service.ProdutoService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("produto")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;
    private final ProdutoReservaService produtoReservaService;

    @GetMapping("/{idPost}")
    private ResponseEntity<List<Produto>> getProdutoByIdPost(@PathVariable Integer idPost) {

        return ResponseEntity.ok(produtoService.findByIdPostIn(Collections.singletonList(idPost)));
    }

    @PostMapping("/reserva")
    private ResponseEntity<List<ProdutoReserva>> save(@RequestBody String body) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(produtoReservaService.saveMany(body));
    }

    @GetMapping("/reserva/{idPost}")
    private ResponseEntity<List<BigDecimal>> findAllSomas(
            @PathVariable Integer idPost) {

        return ResponseEntity.ok(produtoReservaService.calculoReservas(idPost));
    }

    @GetMapping("/reserva/{idPost}/{idUser}")
    private ResponseEntity<List<ProdutoReserva>> findAllByIdPostAndIdUser(
            @PathVariable Integer idPost,@PathVariable Integer idUser) {

        return ResponseEntity.ok(produtoReservaService.findAllByIdPostAndIdUser(idPost, idUser));
    }
}
