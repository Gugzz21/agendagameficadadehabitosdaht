package com.senac.daht.controller;

import com.senac.daht.entity.Personagem;
import com.senac.daht.service.PersonagemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("api/personagem")
@Tag(name = "personagem", description = "API para vizualizar o personagem")
public class PersonagemController {
    private PersonagemService personagemService;

    public PersonagemController(PersonagemService personagemService){
        this.personagemService = personagemService;
    }

    @GetMapping("/listar")
    @Operation(summary = "Ver personagem", description = "Endpoint para mostrar o personagem do usuário")
    public ResponseEntity<Optional<Personagem>> vizualizarPersonagem(
            @RequestParam int id
    ) {
        return ResponseEntity.ok(personagemService.listarPersonagem(id));
    }
}

