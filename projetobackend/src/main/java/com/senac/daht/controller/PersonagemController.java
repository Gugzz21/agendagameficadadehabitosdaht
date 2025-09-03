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
@RequestMapping("/api/personagem")
@Tag(name = "personagem", description = "API para o gerenciamento de personagens")
public class PersonagemController {
    private final PersonagemService personagemService;

    public PersonagemController(PersonagemService personagemService) {
        this.personagemService = personagemService;
    }

    @GetMapping("/listar")
    @Operation(summary = "Listar todos os personagens")
    public ResponseEntity<List<PersonagemDTOResponse>> listarPersonagens() {
        return ResponseEntity.ok(personagemService.listarPersonagens());
    }

    @GetMapping("/listarPorId/{id}")
    @Operation(summary = "Listar personagem por ID")
    public ResponseEntity<PersonagemDTOResponse> listarPorId(@PathVariable("id") Integer id) {
        PersonagemDTOResponse personagem = personagemService.listarPorId(id);
        if (personagem == null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(personagem);
        }
    }

    @PostMapping("/criar")
    @Operation(summary = "Criar novo personagem")
    public ResponseEntity<PersonagemDTOResponse> criarPersonagem(@Valid @RequestBody PersonagemDTORequest personagem) {
        return ResponseEntity.status(HttpStatus.CREATED).body(personagemService.criarPersonagem(personagem));
    }

    @PutMapping("/atualizar/{id}")
    @Operation(summary = "Atualizar todos os dados do personagem")
    public ResponseEntity<PersonagemDTOResponse> atualizarPersonagem(@PathVariable("id") Integer id, @RequestBody PersonagemDTORequest personagemDTORequest) {
        PersonagemDTOResponse response = personagemService.atualizarPersonagem(id, personagemDTORequest);
        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deletar/{id}")
    @Operation(summary = "Deletar personagem por ID")
    public ResponseEntity<Void> deletarPersonagem(@PathVariable("id") Integer id) {
        personagemService.deletarPersonagem(id);
        return ResponseEntity.noContent().build();
    }
}

