package com.senac.daht.controller;

import com.senac.daht.dto.request.TabelaPremioDTORequest;
import com.senac.daht.dto.response.TabelaPremioDTOResponse;
import com.senac.daht.service.TabelaPremioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/tabela-premio")
@Tag(name = "Tabela Prêmio", description = "API para o gerenciamento de tabela de prêmios")
public class TabelaPremioController {

    private final TabelaPremioService tabelaPremioService;

    public TabelaPremioController(TabelaPremioService tabelaPremioService) {
        this.tabelaPremioService = tabelaPremioService;
    }

    @GetMapping("/listar")
    @Operation(summary = "Listar tabelas de prêmios", description = "Endpoint para listar todas as tabelas de prêmios")
    public ResponseEntity<List<TabelaPremioDTOResponse>> listarTabelasPremios() {
        return ResponseEntity.ok(tabelaPremioService.listarTabelasPremios());
    }

    @GetMapping("/listarPorId/{id}")
    @Operation(summary = "Listar tabela de prêmio por ID", description = "Endpoint para buscar uma tabela de prêmio pelo seu ID")
    public ResponseEntity<TabelaPremioDTOResponse> listarPorId(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(tabelaPremioService.listarPorId(id));
    }

    @PostMapping("/criar")
    @Operation(summary = "Criar nova tabela de prêmio", description = "Endpoint para criar um novo registro de tabela de prêmio")
    public ResponseEntity<TabelaPremioDTOResponse> criarTabelaPremio(@Valid @RequestBody TabelaPremioDTORequest tabelaPremioDTORequest) {
        TabelaPremioDTOResponse novaTabelaPremio = tabelaPremioService.criarTabelaPremio(tabelaPremioDTORequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaTabelaPremio);
    }


    @DeleteMapping("/deletar/{id}")
    @Operation(summary = "Deletar tabela de prêmio", description = "Endpoint para deletar uma tabela de prêmio pelo seu ID")
    public ResponseEntity<Void> deletarTabelaPremio(@PathVariable("id") Integer id) {
        tabelaPremioService.deletarTabelaPremio(id);
        return ResponseEntity.noContent().build();
    }
}