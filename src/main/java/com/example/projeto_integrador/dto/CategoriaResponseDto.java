package com.example.projeto_integrador.dto;

import com.example.projeto_integrador.domain.Categoria;

import java.util.UUID;

public record CategoriaResponseDto(
         UUID id,
         String nome
) {
    public CategoriaResponseDto(Categoria categoria) {
        this(
                categoria.getId(),
                categoria.getNome()
        );
    }
}
