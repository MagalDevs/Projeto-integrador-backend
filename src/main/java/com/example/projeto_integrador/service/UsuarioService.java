package com.example.projeto_integrador.service;

import com.example.projeto_integrador.domain.Usuario;
import com.example.projeto_integrador.dto.UsuarioDto;
import com.example.projeto_integrador.repository.UsuarioCompletoRepository;
import com.example.projeto_integrador.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UsuarioService {
    private final UsuarioRepository repository;
    private final UsuarioCompletoRepository completoRepository;

    public UsuarioService(UsuarioRepository repository, UsuarioCompletoRepository completoRepository) {
        this.repository = repository;
        this.completoRepository = completoRepository;
    }

    public List<UsuarioDto> getAllUsers() {
        return completoRepository.findAll();
    }

    public Usuario getUserById(UUID id){
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));
    }
}
