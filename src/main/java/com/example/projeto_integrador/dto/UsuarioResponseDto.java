package com.example.projeto_integrador.dto;

import com.example.projeto_integrador.domain.Usuario;
import com.example.projeto_integrador.domain.enums.Role;

import java.util.UUID;

public record UsuarioResponseDto(
        UUID id,
        String nome,
        Role role
) {
    public UsuarioResponseDto(Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getNome(),
                usuario.getRole()
        );
    }
}
