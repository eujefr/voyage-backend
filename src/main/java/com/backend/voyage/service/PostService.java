package com.backend.voyage.service;

import com.backend.voyage.model.DTO.PostDTO;
import com.backend.voyage.model.Post;
import com.backend.voyage.model.Produto;
import com.backend.voyage.repository.PostRepository;

import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserService userService;
    private final ProdutoService produtoService;

    public List<PostDTO> findAll(Pageable pageable) {

        Page<Post> posts = postRepository.findAllByOrderByIdPostDesc(pageable);

        List<Produto> produto = produtoService.findByIdPostIn(posts.stream()
                .map(Post::getIdPost).collect(Collectors.toList()));

        return PostDTO.postDto(posts.stream().toList(), userService.findAllByidUserIn(posts
                .stream().map(Post::getIdUser).collect(Collectors.toList())), produto);
    }

    public Post save(Post post) {

        return postRepository.save(post);
    }
}
