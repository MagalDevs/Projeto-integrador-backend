package com.example.projeto_integrador.dto;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public record DenunciaRequestCreateDto(
        String titulo,
        String descricao,
        float latitude,
        float longitude,
        UUID usuarioId,
        UUID categoriaId,
        List<MultipartFile> imagens
) {
}
