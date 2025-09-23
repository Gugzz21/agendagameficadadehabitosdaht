package com.senac.daht.dto;

import com.senac.daht.entity.RoleName;

public record CreateUsuarioDto(
        String email,
        String senha,
        RoleName role

) {

}
