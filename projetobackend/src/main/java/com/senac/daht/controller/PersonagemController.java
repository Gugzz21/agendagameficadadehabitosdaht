package com.senac.daht.controller;

import com.senac.daht.dto.request.PersonagemDTORequest;
import com.senac.daht.dto.response.PersonagemDTOResponse;
import com.senac.daht.service.PersonagemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/personagem")
@Tag(name = "Personagem", description = "API para o gerenciamento de personagens")
public class PersonagemController {

    private final PersonagemService personagemService;

    public PersonagemController(PersonagemService personagemService) {
        this.personagemService = personagemService;
    }

    @GetMapping("/listar")
    @Operation(summary = "Listar personagens", description = "Endpoint para listar todos os personagens")
    public ResponseEntity<List<PersonagemDTOResponse>> listarPersonagens() {
        return ResponseEntity.ok(personagemService.listarPersonagens());
    }

    @GetMapping("/listarPorId/{id}")
    @Operation(summary = "Listar personagem por ID", description = "Endpoint para buscar um personagem pelo seu ID")
    public ResponseEntity<PersonagemDTOResponse> listarPorId(@PathVariable("id") Integer id) {
        PersonagemDTOResponse personagem = personagemService.listarPorId(id);
        return ResponseEntity.ok(personagem);
    }

    @PostMapping("/criar")
    @Operation(summary = "Criar novo personagem", description = "Endpoint para criar um novo registro de personagem")
    public ResponseEntity<PersonagemDTOResponse> criarPersonagem(@Valid @RequestBody PersonagemDTORequest personagemDTORequest) {
        PersonagemDTOResponse novoPersonagem = personagemService.criarPersonagem(personagemDTORequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoPersonagem);
    }

    @PutMapping("/atualizar/{id}")
    @Operation(summary = "Atualizar personagem", description = "Endpoint para atualizar todos os dados de um personagem")
    public ResponseEntity<PersonagemDTOResponse> atualizarPersonagem(
            @PathVariable("id") Integer id,
            @Valid @RequestBody PersonagemDTORequest personagemDTORequest) {
        PersonagemDTOResponse personagemAtualizado = personagemService.atualizarPersonagem(id, personagemDTORequest);
        return ResponseEntity.ok(personagemAtualizado);
    }

    @DeleteMapping("/deletar/{id}")
    @Operation(summary = "Deletar personagem", description = "Endpoint para deletar um personagem pelo seu ID")
    public ResponseEntity<Void> deletarPersonagem(@PathVariable("id") Integer id) {
        personagemService.deletarPersonagem(id);
        return ResponseEntity.noContent().build();
    }
}