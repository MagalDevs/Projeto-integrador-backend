package com.example.projeto_integrador.service;

import com.example.projeto_integrador.domain.Categoria;
import com.example.projeto_integrador.domain.Denuncia;
import com.example.projeto_integrador.domain.Usuario;
import com.example.projeto_integrador.domain.enums.StatusEnum;
import com.example.projeto_integrador.dto.DenunciaRequestCreateDto;
import com.example.projeto_integrador.repository.DenunciaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class DenunciaService {
    private final DenunciaRepository repository;
    private final UsuarioService usuarioService;
    private final CategoriaService categoriaService;
    private final ImagemService imagemService;

    public DenunciaService(DenunciaRepository repository, UsuarioService usuarioService, CategoriaService categoriaService, ImagemService imagemService) {
        this.repository = repository;
        this.usuarioService = usuarioService;
        this.categoriaService = categoriaService;
        this.imagemService = imagemService;
    }

    public List<Denuncia> getAllDenuncias() {
        return repository.findAllWithImagens();
    }

    public Denuncia getDenunciaById(UUID id) {
        return repository.findByIdWithImagens(id)
                .orElseThrow(() -> new RuntimeException("Denúncia não encontrada!"));
    }

    public List<Denuncia> getDenunciasByUsuarioId(UUID usuarioId) {
        Usuario usuario = usuarioService.getUserById(usuarioId);
        return repository.findAllByUsuarioIdWithImagens(usuario.getId());
    }

    @Transactional
    public Denuncia createDenuncia(DenunciaRequestCreateDto dto) throws IOException {
        Usuario usuario = usuarioService.getUserById(dto.usuarioId());
        Categoria categoria = categoriaService.getCategoryById(dto.categoriaId());

        Denuncia denuncia = repository.save(new Denuncia(dto, usuario, categoria));

        if (dto.imagens() != null && !dto.imagens().isEmpty()) {
            imagemService.salvarImagens(dto.imagens(), denuncia);
        }

        return denuncia;
    }

    public Denuncia updateStatus(UUID uuid, StatusEnum statusEnum) {
        Denuncia denuncia = getDenunciaById(uuid);
        denuncia.setStatus(statusEnum);
        return repository.save(getDenunciaById(uuid));
    }

    public Denuncia updateDevolutiva(UUID uuid, String devolutiva) {
        Denuncia denuncia = getDenunciaById(uuid);
        denuncia.setDevolutiva(devolutiva);
        return repository.save(getDenunciaById(uuid));
    }
}
