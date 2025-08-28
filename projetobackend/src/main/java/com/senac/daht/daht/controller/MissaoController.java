package com.senac.daht.daht.controller;

import com.senac.daht.daht.entity.Missao;
import com.senac.daht.daht.service.MissaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/missao")
@Tag(name = "missao", description = "API para o gerenciamento de missao")
public class MissaoController {

    private MissaoService missaoService;

    public MissaoController(MissaoService missaoService){
        this.missaoService = missaoService;
    }

   @GetMapping("/listar")
    @Operation(summary = "Listar missao", description = "Endpoint para listar todas as missoes")
    public ResponseEntity<List<Missao>> listarMissoes(
    @RequestParam int id,
    @RequestParam int status) {return ResponseEntity.ok(missaoService.listarMissoes(id, status));}





        }


