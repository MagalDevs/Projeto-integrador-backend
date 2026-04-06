package com.example.projeto_integrador.service;

import com.example.projeto_integrador.domain.Categoria;
import com.example.projeto_integrador.domain.Denuncia;
import com.example.projeto_integrador.domain.Usuario;
import com.example.projeto_integrador.dto.DenunciaRequestCreateDto;
import com.example.projeto_integrador.repository.DenunciaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class DenunciaService {
    private final DenunciaRepository repository;
    private final UsuarioService usuarioService;
    private final CategoriaService categoriaService;

    public DenunciaService(DenunciaRepository repository, UsuarioService usuarioService, CategoriaService categoriaService) {
        this.repository = repository;
        this.usuarioService = usuarioService;
        this.categoriaService = categoriaService;
    }

    public List<Denuncia> getAllDenuncias(){
        return repository.findAll();
    }

    public Denuncia getDenunciaById(UUID id){
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Denúncia não encontrada!"));
    }

    public List<Denuncia> getDenunciasByUsuarioId(UUID usuarioId){
        Usuario usuario = usuarioService.getUserById(usuarioId);
        return repository.findAllByUsuarioId(usuario.getId());
    }

    @Transactional
    public Denuncia createDenuncia(DenunciaRequestCreateDto denuncia){
        Usuario usuario = usuarioService.getUserById(denuncia.usuarioId());
        Categoria categoria = categoriaService.getCategoryById(denuncia.categoriaId());
        return repository.save(new Denuncia(denuncia, usuario, categoria));
    }
}
