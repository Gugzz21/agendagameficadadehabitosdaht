package com.senac.daht.controller;

import com.senac.daht.dto.request.UsuarioDTORequest;
import com.senac.daht.dto.response.UsuarioDTOResponse;
import com.senac.daht.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/usuario")
@Tag(name = "Usuário", description = "API para o gerenciamento de usuários")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/listar")
    @Operation(summary = "Listar usuários", description = "Endpoint para listar todos os usuários")
    public ResponseEntity<List<UsuarioDTOResponse>> listarUsuarios() {
        return ResponseEntity.ok(usuarioService.listarUsuarios());
    }

    @GetMapping("/listarPorId/{id}")
    @Operation(summary = "Listar usuário por ID", description = "Endpoint para buscar um usuário pelo seu ID")
    public ResponseEntity<UsuarioDTOResponse> listarPorId(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(usuarioService.listarPorId(id));
    }

    @PostMapping("/criar")
    @Operation(summary = "Criar novo usuário", description = "Endpoint para criar um novo registro de usuário")
    public ResponseEntity<UsuarioDTOResponse> criarUsuario(@RequestBody UsuarioDTORequest usuarioDTORequest) {
        UsuarioDTOResponse novoUsuario = usuarioService.criarUsuario(usuarioDTORequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
    }

    @PutMapping("/atualizar/{id}")
    @Operation(summary = "Atualizar usuário", description = "Endpoint para atualizar todos os dados de um usuário")
    public ResponseEntity<UsuarioDTOResponse> atualizarUsuario(
            @PathVariable("id") Integer id,
            @RequestBody UsuarioDTORequest usuarioDTORequest) {
        UsuarioDTOResponse usuarioAtualizado = usuarioService.atualizarUsuario(id, usuarioDTORequest);
        return ResponseEntity.ok(usuarioAtualizado);
    }

    @DeleteMapping("/deletar/{id}")
    @Operation(summary = "Deletar usuário", description = "Endpoint para deletar um usuário pelo seu ID")
    public ResponseEntity<Void> deletarUsuario(@PathVariable("id") Integer id) {
        usuarioService.deletarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}