package com.senac.daht.controller;

import com.senac.daht.dto.request.MissaoDTORequest;
import com.senac.daht.dto.response.MissaoDTOResponse;
import com.senac.daht.service.MissaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/missao")
@Tag(name = "Missão", description = "API para o gerenciamento de missões")
public class MissaoController {

    private final MissaoService missaoService;

    public MissaoController(MissaoService missaoService) {
        this.missaoService = missaoService;
    }

    @GetMapping("/listar")
    @Operation(summary = "Listar missões", description = "Endpoint para listar todas as missões")
    public ResponseEntity<List<MissaoDTOResponse>> listarMissoes() {
        return ResponseEntity.ok(missaoService.listarMissoes());
    }

    @GetMapping("/listarPorId/{id}")
    @Operation(summary = "Listar missão por ID", description = "Endpoint para buscar uma missão pelo seu ID")
    public ResponseEntity<MissaoDTOResponse> listarPorId(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(missaoService.listarPorId(id));
    }

    @PostMapping("/criar")
    @Operation(summary = "Criar nova missão", description = "Endpoint para criar um novo registro de missão")
    public ResponseEntity<MissaoDTOResponse> criarMissao(@Valid @RequestBody MissaoDTORequest missaoDTORequest) {
        MissaoDTOResponse novaMissao = missaoService.criarMissao(missaoDTORequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaMissao);
    }

    @PutMapping("/atualizar/{id}")
    @Operation(summary = "Atualizar missão", description = "Endpoint para atualizar todos os dados de uma missão")
    public ResponseEntity<MissaoDTOResponse> atualizarMissao(
            @PathVariable("id") Integer id,
            @Valid @RequestBody MissaoDTORequest missaoDTORequest) {
        MissaoDTOResponse missaoAtualizada = missaoService.atualizarMissao(id, missaoDTORequest);
        return ResponseEntity.ok(missaoAtualizada);
    }

    @DeleteMapping("/deletar/{id}")
    @Operation(summary = "Deletar missão", description = "Endpoint para deletar uma missão pelo seu ID")
    public ResponseEntity<Void> deletarMissao(@PathVariable("id") Integer id) {
        missaoService.deletarMissao(id);
        return ResponseEntity.noContent().build();
    }
}