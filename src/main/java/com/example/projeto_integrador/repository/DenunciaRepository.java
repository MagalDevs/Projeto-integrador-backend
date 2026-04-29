package com.example.projeto_integrador.repository;

import com.example.projeto_integrador.domain.Denuncia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DenunciaRepository extends JpaRepository<Denuncia, UUID> {
    @Query("SELECT DISTINCT d FROM Denuncia d LEFT JOIN FETCH d.imagens")
    List<Denuncia> findAllWithImagens();

    @Query("SELECT d FROM Denuncia d LEFT JOIN FETCH d.imagens WHERE d.id = :id")
    Optional<Denuncia> findByIdWithImagens(@Param("id") UUID id);

    @Query("SELECT DISTINCT d FROM Denuncia d LEFT JOIN FETCH d.imagens WHERE d.usuario.id = :usuarioId")
    List<Denuncia> findAllByUsuarioIdWithImagens(@Param("usuarioId") UUID usuarioId);
}
