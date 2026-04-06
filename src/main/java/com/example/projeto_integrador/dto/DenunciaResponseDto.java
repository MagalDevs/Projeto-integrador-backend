package com.example.projeto_integrador.dto;

import com.example.projeto_integrador.domain.Denuncia;
import com.example.projeto_integrador.domain.enums.StatusEnum;

import java.time.Instant;
import java.util.UUID;

public record DenunciaResponseDto(
        UUID id,
        String titulo,
        String descricao,
        StatusEnum status,
        float latitude,
        float longitude,
        Instant createdAt,
        UsuarioResponseDto usuario,
        CategoriaResponseDto categoria
) {
    public DenunciaResponseDto(Denuncia denuncia) {
        this(
                denuncia.getId(),
                denuncia.getTitulo(),
                denuncia.getDescricao(),
                denuncia.getStatus(),
                denuncia.getLatitude(),
                denuncia.getLongitude(),
                denuncia.getCreatedAt(),
                new UsuarioResponseDto(denuncia.getUsuario()),
                new CategoriaResponseDto(denuncia.getCategoria())
        );
    }
}
