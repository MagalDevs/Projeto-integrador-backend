package com.example.projeto_integrador.dto;

import jakarta.validation.constraints.NotBlank;

public record UsuarioRequestDto(
        @NotBlank
        String email,
        @NotBlank
        String password,
        @NotBlank
        String nome
) {
}
