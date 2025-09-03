package com.senac.daht.controller;

import com.senac.daht.dto.request.UsuarioDTORequest;
import com.senac.daht.dto.response.UsuarioDTOResponse;
import com.senac.daht.dto.response.UsuarioDTOUpdateResponse;
import com.senac.daht.entity.Usuario;
import com.senac.daht.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuario")
@Tag(name = "usuario", description = "API para o gerenciamento de usuários")
public class UsuarioController {
    private UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/listar")
    @Operation(summary = "Listar todos os usuários")
    public ResponseEntity<List<UsuarioDTOResponse>> listarUsuarios() {
        return ResponseEntity.ok(usuarioService.listarUsuarios());
    }

    @GetMapping("/listarPorId/{id}")
    @Operation(summary = "Listar usuário por ID")
    public ResponseEntity<UsuarioDTOResponse> listarPorId(@PathVariable("id") Integer id) {
        UsuarioDTOResponse usuario = usuarioService.listarPorId(id);
        if (usuario == null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(usuario);
        }
    }

    @PostMapping("/criar")
    @Operation(summary = "Criar novo usuário")
    public ResponseEntity<UsuarioDTOResponse> criarUsuario(@Valid @RequestBody UsuarioDTORequest usuario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.criarUsuario(usuario));
    }

    @PutMapping("/atualizar/{id}")
    @Operation(summary = "Atualizar todos os dados do usuário")
    public ResponseEntity<UsuarioDTOResponse> atualizarUsuario(@PathVariable("id") Integer id, @RequestBody UsuarioDTORequest usuarioDTORequest) {
        UsuarioDTOResponse response = usuarioService.atualizarUsuario(id, usuarioDTORequest);
        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/atualizarStatus/{id}")
    @Operation(summary = "Atualizar campo status do usuário")
    public ResponseEntity<UsuarioDTOUpdateResponse> atualizarStatusUsuario(@PathVariable("id") Integer id, @RequestBody UsuarioDTORequest usuarioDTOUpdateRequest) {
        UsuarioDTOUpdateResponse response = usuarioService.atualizarStatusUsuario(id, usuarioDTOUpdateRequest);
        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deletar/{id}")
    @Operation(summary = "Deletar usuário por ID")
    public ResponseEntity<Void> deletarUsuario(@PathVariable("id") Integer id) {
        usuarioService.deletarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
