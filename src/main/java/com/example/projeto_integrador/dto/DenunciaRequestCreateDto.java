package com.example.projeto_integrador.dto;

import java.util.UUID;

public record DenunciaRequestCreateDto(
        String titulo,
        String descricao,
        float latitude,
        float longitude,
        UUID usuarioId,
        UUID categoriaId
) {
}
