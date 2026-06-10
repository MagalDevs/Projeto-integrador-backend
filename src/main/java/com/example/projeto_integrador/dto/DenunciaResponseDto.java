package com.example.projeto_integrador.dto;

import com.example.projeto_integrador.domain.Denuncia;
import com.example.projeto_integrador.domain.Imagem;
import com.example.projeto_integrador.domain.enums.StatusEnum;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record DenunciaResponseDto(
        UUID id,
        String titulo,
        String descricao,
        String devolutiva,
        StatusEnum status,
        float latitude,
        float longitude,
        Instant createdAt,
        UsuarioResponseDto usuario,
        CategoriaResponseDto categoria,
        List<String> imagens
) {
    public DenunciaResponseDto(Denuncia denuncia) {
        this(
                denuncia.getId(),
                denuncia.getTitulo(),
                denuncia.getDescricao(),
                denuncia.getDevolutiva(),
                denuncia.getStatus(),
                denuncia.getLatitude(),
                denuncia.getLongitude(),
                denuncia.getCreatedAt(),
                new UsuarioResponseDto(denuncia.getUsuario()),
                new CategoriaResponseDto(denuncia.getCategoria()),
                denuncia.getImagens().stream().map(Imagem::getUrl).toList()
        );
    }
}
