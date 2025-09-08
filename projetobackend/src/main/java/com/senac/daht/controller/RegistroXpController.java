package com.senac.daht.controller;

import com.senac.daht.dto.request.RegistroXpDTORequest;
import com.senac.daht.dto.response.RegistroXpDTOResponse;
import com.senac.daht.service.RegistroXpService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/registro-xp")
@Tag(name = "Registro de XP", description = "API para o gerenciamento de registros de XP")
public class RegistroXpController {

    private final RegistroXpService registroXpService;

    public RegistroXpController(RegistroXpService registroXpService) {
        this.registroXpService = registroXpService;
    }

    @GetMapping("/listar")
    @Operation(summary = "Listar registros de XP", description = "Endpoint para listar todos os registros de XP")
    public ResponseEntity<List<RegistroXpDTOResponse>> listarRegistrosXp() {
        return ResponseEntity.ok(registroXpService.listarRegistrosXp());
    }

    @GetMapping("/listarPorId/{id}")
    @Operation(summary = "Listar registro de XP por ID", description = "Endpoint para buscar um registro de XP pelo seu ID")
    public ResponseEntity<RegistroXpDTOResponse> listarPorId(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(registroXpService.listarPorId(id));
    }

    @PostMapping("/criar")
    @Operation(summary = "Criar novo registro de XP", description = "Endpoint para criar um novo registro de XP")
    public ResponseEntity<RegistroXpDTOResponse> criarRegistroXp(@Valid @RequestBody RegistroXpDTORequest registroXpDTORequest) {
        RegistroXpDTOResponse novoRegistroXp = registroXpService.criarRegistroXp(registroXpDTORequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoRegistroXp);
    }

    @PutMapping("/atualizar/{id}")
    @Operation(summary = "Atualizar registro de XP", description = "Endpoint para atualizar todos os dados de um registro de XP")
    public ResponseEntity<RegistroXpDTOResponse> atualizarRegistroXp(
            @PathVariable("id") Integer id,
            @Valid @RequestBody RegistroXpDTORequest registroXpDTORequest) {
        RegistroXpDTOResponse registroXpAtualizado = registroXpService.atualizarRegistroXp(id, registroXpDTORequest);
        return ResponseEntity.ok(registroXpAtualizado);
    }

    @DeleteMapping("/deletar/{id}")
    @Operation(summary = "Deletar registro de XP", description = "Endpoint para deletar um registro de XP pelo seu ID")
    public ResponseEntity<Void> deletarRegistroXp(@PathVariable("id") Integer id) {
        registroXpService.deletarRegistroXp(id);
        return ResponseEntity.noContent().build();
    }
}