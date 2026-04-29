package com.example.projeto_integrador.repository;

import com.example.projeto_integrador.domain.Imagem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ImagemRepository extends JpaRepository<Imagem, UUID> {
}
