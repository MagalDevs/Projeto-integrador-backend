package com.example.projeto_integrador.repository;

import com.example.projeto_integrador.domain.Denuncia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DenunciaRepository extends JpaRepository<Denuncia, UUID> {
    List<Denuncia> findAllByUsuarioId(UUID id);
}
