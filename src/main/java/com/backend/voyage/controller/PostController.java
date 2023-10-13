package com.backend.voyage.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.backend.voyage.model.Post;
import com.backend.voyage.model.Produto;
import com.backend.voyage.model.User;
import com.backend.voyage.service.ProdutoService;
import com.backend.voyage.service.UserService;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.backend.voyage.model.DTO.PostDTO;
import com.backend.voyage.service.PostService;

import lombok.RequiredArgsConstructor;

@RequestMapping("post")
@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final UserService userService;
    private final ProdutoService produtoService;

    @GetMapping("/posts")
    public List<PostDTO> findAll(@PageableDefault(size = 5) Pageable pageable) {

        return postService.findAll(pageable);
    }

    @PostMapping("/save")
    public ResponseEntity<Post> savePost(@RequestBody String postJson) throws JSONException {

        JSONObject jsonObject = new JSONObject(postJson);
        User user = userService.findBydsEmailUsuario(
                jsonObject.get("dsEmailUsuario").toString());

        Post post = new Post();
        post.setIdUser(user.getIdUser());
        post.setDsPost(jsonObject.get("dsPost").toString());
        post.setDsImgPost(jsonObject.get("dsImgPost").toString());

        Post newPost = postService.save(post);

        List<Produto> produtoJson = Produto.fromJson(postJson);

        if (!produtoJson.isEmpty()) {

            produtoJson = produtoJson.stream()
                    .peek(produto -> produto.setIdPost(newPost.getIdPost())).collect(Collectors.toList());

             produtoService.saveMany(produtoJson);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(newPost);
    }
}
