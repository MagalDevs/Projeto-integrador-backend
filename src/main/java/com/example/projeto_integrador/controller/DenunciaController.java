package com.example.projeto_integrador.controller;

import com.example.projeto_integrador.dto.DenunciaRequestCreateDto;
import com.example.projeto_integrador.dto.DenunciaResponseDto;
import com.example.projeto_integrador.service.DenunciaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/denuncias")
public class DenunciaController {
    private final DenunciaService service;

    public DenunciaController(DenunciaService service) {
        this.service = service;
    }

    @GetMapping("/admin")
    public ResponseEntity<List<DenunciaResponseDto>> getAllDenuncias() {
        return ResponseEntity.ok(service.getAllDenuncias().stream().map(denuncia -> new DenunciaResponseDto(denuncia)).toList());
    }

    @GetMapping
    public ResponseEntity<DenunciaResponseDto> createDenuncia(@RequestBody DenunciaRequestCreateDto dto) {
        return ResponseEntity.ok(new DenunciaResponseDto(service.createDenuncia(dto)));
    }
}
