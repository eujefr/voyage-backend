package com.backend.voyage.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import static java.util.Objects.nonNull;

@Entity
@Getter
@Setter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_post")
    private Integer idPost;

    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    private Integer idUser;

    @Column(name = "ds_post")
    private String dsPost;

    @Column(name = "ds_img_post")
    private String dsImgPost;

}
