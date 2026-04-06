package com.example.projeto_integrador.controller;

import com.example.projeto_integrador.dto.DenunciaRequestCreateDto;
import com.example.projeto_integrador.dto.DenunciaResponseDto;
import com.example.projeto_integrador.service.DenunciaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

    @GetMapping("/{id}")
    public ResponseEntity<DenunciaResponseDto> getDenunciaById(@PathVariable String id) {
        return ResponseEntity.ok(new DenunciaResponseDto(service.getDenunciaById(UUID.fromString(id))));
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<DenunciaResponseDto>> getDenunciasByUsuarioId(@PathVariable String usuarioId) {
        return ResponseEntity.ok(service.getDenunciasByUsuarioId(UUID.fromString(usuarioId)).stream().map(denuncia -> new DenunciaResponseDto(denuncia)).toList());
    }

    @PostMapping
    public ResponseEntity<DenunciaResponseDto> createDenuncia(@RequestBody DenunciaRequestCreateDto dto) {
        return ResponseEntity.ok(new DenunciaResponseDto(service.createDenuncia(dto)));
    }
}
