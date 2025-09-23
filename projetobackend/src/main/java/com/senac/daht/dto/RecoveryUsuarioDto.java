package com.senac.daht.dto;

import com.senac.daht.entity.Role;

import java.util.List;

public record RecoveryUsuarioDto(Long id,
                                 String email,
                                 List<Role> roles) {
}
