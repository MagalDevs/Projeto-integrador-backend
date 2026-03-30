package com.example.projeto_integrador.repository;

import com.example.projeto_integrador.dto.UsuarioDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UsuarioCompletoRepository extends JpaRepository<UsuarioDto, UUID> {
}
