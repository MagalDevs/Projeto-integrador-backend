package com.example.projeto_integrador.dto;

import jakarta.validation.constraints.NotBlank;

public record CategoriaRequestCreateDto(
        @NotBlank
        String nome
) {
}
