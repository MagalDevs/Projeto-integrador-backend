package com.example.projeto_integrador.controller;

import com.example.projeto_integrador.dto.UsuarioDto;
import com.example.projeto_integrador.dto.UsuarioRequestDto;
import com.example.projeto_integrador.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @GetMapping("/admin")
    public ResponseEntity<List<UsuarioDto>> getAllUsers(){
        return ResponseEntity.ok(service.getAllUsers());
    }

    @PostMapping
    public ResponseEntity<UsuarioDto> createUser(@RequestBody UsuarioRequestDto request){
        return ResponseEntity.ok(service.createUser(request));
    }
}
