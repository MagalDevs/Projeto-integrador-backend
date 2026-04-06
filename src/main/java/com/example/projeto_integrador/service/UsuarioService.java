package com.example.projeto_integrador.service;

import com.example.projeto_integrador.domain.Usuario;
import com.example.projeto_integrador.domain.enums.Role;
import com.example.projeto_integrador.dto.UsuarioDto;
import com.example.projeto_integrador.dto.UsuarioRequestDto;
import com.example.projeto_integrador.repository.UsuarioCompletoRepository;
import com.example.projeto_integrador.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class UsuarioService {
    private final UsuarioRepository repository;
    private final UsuarioCompletoRepository completoRepository;
    private final SupabaseService supabaseService;

    public UsuarioService(UsuarioRepository repository, UsuarioCompletoRepository completoRepository, SupabaseService supabaseService) {
        this.repository = repository;
        this.completoRepository = completoRepository;
        this.supabaseService = supabaseService;
    }

    public List<UsuarioDto> getAllUsers() {
        return completoRepository.findAll();
    }

    public Usuario getUserById(UUID id){
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));
    }

    public UsuarioDto getCompleteUserById(UUID id) {
        return completoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));
    }

    @Transactional
    public UsuarioDto createUser(UsuarioRequestDto request) {
        String userId = supabaseService.createAuthUser(request.email(), request.password());
        Usuario usuario = new Usuario(request, userId, Role.USER);
        repository.save(usuario);
        return new UsuarioDto(usuario.getId(), usuario.getNome(), request.email(), usuario.getRole());
    }

    @Transactional
    public Usuario updateUser(UsuarioRequestDto request, UUID id) {
        Usuario usuario = getUserById(id);
        usuario.updateData(request);
        return repository.save(usuario);
    }
}
