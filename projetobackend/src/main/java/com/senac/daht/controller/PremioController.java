package com.senac.daht.controller;

import com.senac.daht.dto.request.PremioDTORequest;
import com.senac.daht.dto.response.PremioDTOResponse;
import com.senac.daht.entity.Premio;
import com.senac.daht.service.PremioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/premio")
@Tag(name = "premio", description = "API para o gerenciamento de prêmios")
public class PremioController {
    private final PremioService premioService;

    public PremioController(PremioService premioService) {
        this.premioService = premioService;
    }
    @GetMapping("/listar")
    @Operation(summary = "Listar todos os prêmios")
    public ResponseEntity<List<Premio>> listarPremios() {
        return ResponseEntity.ok(premioService.listarPremios());
    }

    @PostMapping("/criar")
    @Operation(summary = "Criar novo prêmio")
    public ResponseEntity<PremioDTOResponse> criarPremio(@Valid @RequestBody PremioDTORequest premio) {
        return ResponseEntity.status(HttpStatus.CREATED).body(premioService.criarPremio(premio));
    }

    @PutMapping("/atualizar/{id}")
    @Operation(summary = "Atualizar todos os dados do prêmio")
    public ResponseEntity<PremioDTOResponse> atualizarPremio(@PathVariable("id") Integer id, @RequestBody PremioDTORequest premioDTORequest) {
        PremioDTOResponse response = premioService.atualizarPremio(id, premioDTORequest);
        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deletar/{id}")
    @Operation(summary = "Deletar prêmio por ID")
    public ResponseEntity<Void> deletarPremio(@PathVariable("id") Integer id) {
        premioService.deletarPremio(id);
        return ResponseEntity.noContent().build();
    }
}
