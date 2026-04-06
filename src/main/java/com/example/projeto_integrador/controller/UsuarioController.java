package com.example.projeto_integrador.controller;

import com.example.projeto_integrador.domain.Usuario;
import com.example.projeto_integrador.dto.UsuarioDto;
import com.example.projeto_integrador.dto.UsuarioRequestDto;
import com.example.projeto_integrador.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

    @PatchMapping("/{id}")
    public ResponseEntity<UsuarioDto> updateUser(@RequestBody UsuarioRequestDto request, @PathVariable String id){
        Usuario usuario = service.updateUser(request, UUID.fromString(id));
        return ResponseEntity.ok(service.getCompleteUserById(usuario.getId()));
    }
}
