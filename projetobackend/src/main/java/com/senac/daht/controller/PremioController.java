package com.senac.daht.controller;

import com.senac.daht.entity.Premio;
import com.senac.daht.service.PremioService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/premio")
public class PremioController {
    private PremioService premioService;

    public PremioController(PremioService premioService){
        this.premioService = premioService;
    }

    @GetMapping("/listar")
    @Operation(summary = "Listar premios", description = "API para listar premios")
    public ResponseEntity<List<Premio>> listarPremios(){
        return ResponseEntity.ok(premioService.listarPremios());
    }
}
