package com.senac.daht.controller;

import com.senac.daht.entity.Usuario;
import com.senac.daht.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/usuario")
public class UsuarioController {
    private UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @GetMapping("/listar")
    @Operation(summary = "Listar usuarios", description = "API para listar usuarios")
    public ResponseEntity<List<Usuario>> listarUsuario(){
        return ResponseEntity.ok(usuarioService.listarUsuarios());
    }

}
