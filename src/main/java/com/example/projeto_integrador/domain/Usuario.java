package com.example.projeto_integrador.domain;

import com.example.projeto_integrador.domain.enums.Role;
import com.example.projeto_integrador.dto.UsuarioRequestDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario {
    @Id
    private UUID id;
    private String nome;
    @Enumerated(EnumType.STRING)
    private Role role;

    public Usuario(UsuarioRequestDto request, String userId, Role role) {
        this.id = UUID.fromString(userId);
        this.nome = request.nome();
        this.role = role;
    }

    public void updateData(UsuarioRequestDto request) {
        if (request.nome() != null && !request.nome().isBlank() && request.nome() != this.nome) {
            this.nome = request.nome();
        }
    }
}
