package com.senac.daht.controller;

import com.senac.daht.dto.request.RegistroOuroDTORequest;
import com.senac.daht.dto.response.RegistroOuroDTOResponse;
import com.senac.daht.service.RegistroOuroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/registro-ouro")
@Tag(name = "Registro de Ouro", description = "API para o gerenciamento de registros de ouro")
public class RegistroOuroController {

    private final RegistroOuroService registroOuroService;

    public RegistroOuroController(RegistroOuroService registroOuroService) {
        this.registroOuroService = registroOuroService;
    }

    @GetMapping("/listar")
    @Operation(summary = "Listar registros de ouro", description = "Endpoint para listar todos os registros de ouro")
    public ResponseEntity<List<RegistroOuroDTOResponse>> listarRegistrosOuro() {
        return ResponseEntity.ok(registroOuroService.listarRegistrosOuro());
    }

    @GetMapping("/listarPorId/{id}")
    @Operation(summary = "Listar registro de ouro por ID", description = "Endpoint para buscar um registro de ouro pelo seu ID")
    public ResponseEntity<RegistroOuroDTOResponse> listarPorId(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(registroOuroService.listarPorId(id));
    }

    @PostMapping("/criar")
    @Operation(summary = "Criar novo registro de ouro", description = "Endpoint para criar um novo registro de ouro")
    public ResponseEntity<RegistroOuroDTOResponse> criarRegistroOuro(@Valid @RequestBody RegistroOuroDTORequest registroOuroDTORequest) {
        RegistroOuroDTOResponse novoRegistroOuro = registroOuroService.criarRegistroOuro(registroOuroDTORequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoRegistroOuro);
    }

    @PutMapping("/atualizar/{id}")
    @Operation(summary = "Atualizar registro de ouro", description = "Endpoint para atualizar todos os dados de um registro de ouro")
    public ResponseEntity<RegistroOuroDTOResponse> atualizarRegistroOuro(
            @PathVariable("id") Integer id,
            @Valid @RequestBody RegistroOuroDTORequest registroOuroDTORequest) {
        RegistroOuroDTOResponse registroOuroAtualizado = registroOuroService.atualizarRegistroOuro(id, registroOuroDTORequest);
        return ResponseEntity.ok(registroOuroAtualizado);
    }

    @DeleteMapping("/deletar/{id}")
    @Operation(summary = "Deletar registro de ouro", description = "Endpoint para deletar um registro de ouro pelo seu ID")
    public ResponseEntity<Void> deletarRegistroOuro(@PathVariable("id") Integer id) {
        registroOuroService.deletarRegistroOuro(id);
        return ResponseEntity.noContent().build();
    }
}