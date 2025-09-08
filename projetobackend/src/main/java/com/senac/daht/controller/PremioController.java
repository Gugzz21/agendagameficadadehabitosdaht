package com.senac.daht.controller;

import com.senac.daht.dto.request.PremioDTORequest;
import com.senac.daht.dto.response.PremioDTOResponse;
import com.senac.daht.service.PremioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/premio")
@Tag(name = "Prêmio", description = "API para o gerenciamento de prêmios")
public class PremioController {

    private final PremioService premioService;

    public PremioController(PremioService premioService) {
        this.premioService = premioService;
    }

    @GetMapping("/listar")
    @Operation(summary = "Listar prêmios", description = "Endpoint para listar todos os prêmios")
    public ResponseEntity<List<PremioDTOResponse>> listarPremios() {
        return ResponseEntity.ok(premioService.listarPremios());
    }

    @GetMapping("/listarPorId/{id}")
    @Operation(summary = "Listar prêmio por ID", description = "Endpoint para buscar um prêmio pelo seu ID")
    public ResponseEntity<PremioDTOResponse> listarPorId(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(premioService.listarPorId(id));
    }

    @PostMapping("/criar")
    @Operation(summary = "Criar novo prêmio", description = "Endpoint para criar um novo registro de prêmio")
    public ResponseEntity<PremioDTOResponse> criarPremio(@Valid @RequestBody PremioDTORequest premioDTORequest) {
        PremioDTOResponse novoPremio = premioService.criarPremio(premioDTORequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoPremio);
    }

    @PutMapping("/atualizar/{id}")
    @Operation(summary = "Atualizar prêmio", description = "Endpoint para atualizar todos os dados de um prêmio")
    public ResponseEntity<PremioDTOResponse> atualizarPremio(
            @PathVariable("id") Integer id,
            @Valid @RequestBody PremioDTORequest premioDTORequest) {
        PremioDTOResponse premioAtualizado = premioService.atualizarPremio(id, premioDTORequest);
        return ResponseEntity.ok(premioAtualizado);
    }

    @DeleteMapping("/deletar/{id}")
    @Operation(summary = "Deletar prêmio", description = "Endpoint para deletar um prêmio pelo seu ID")
    public ResponseEntity<Void> deletarPremio(@PathVariable("id") Integer id) {
        premioService.deletarPremio(id);
        return ResponseEntity.noContent().build();
    }
}