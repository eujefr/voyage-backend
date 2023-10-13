package com.backend.voyage.model.DTO;

import java.util.List;
import java.util.stream.Collectors;

import com.backend.voyage.model.Post;
import com.backend.voyage.model.Produto;
import com.backend.voyage.model.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostDTO {

    private Integer idPost;
    private String dsPost;
    private String dsImgPost;
    private UserDTO userDTO;
    private boolean containsProduto;

    public PostDTO(Post posts, User users, boolean containsProduto) {

        this.idPost = posts.getIdPost();
        this.userDTO = new UserDTO(users);
        this.dsPost = posts.getDsPost();
        this.dsImgPost = posts.getDsImgPost();
        this.containsProduto = containsProduto;
    }

    public static List<PostDTO> postDto(List<Post> post, List<User> users, List<Produto> produtos ) {

        return post.stream().map(post1 -> {

            boolean postContainsProduto
                    = produtos.stream().anyMatch(produto -> produto.getIdPost().equals(post1.getIdPost()));

            return new PostDTO(post1, users.stream().filter(user ->
                    user.getIdUser().equals(post1.getIdUser())).findFirst().get(), postContainsProduto);

        }).collect(Collectors.toList());
    }

}
