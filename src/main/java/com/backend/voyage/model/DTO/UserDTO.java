package com.backend.voyage.model.DTO;

import com.backend.voyage.model.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    private String dsUser;

    private String dsEmailUsuario;

    private String dsImgUser;

    public UserDTO(User user) {

        dsUser = user.getDsUser();
        dsEmailUsuario = user.getDsEmailUsuario();
        dsImgUser = user.getDsImgUser();
    }
}
