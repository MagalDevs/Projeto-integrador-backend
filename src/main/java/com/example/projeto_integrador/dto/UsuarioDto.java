package com.example.projeto_integrador.dto;

import com.example.projeto_integrador.domain.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "usuarios_completo")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class UsuarioDto {
    @Id
    private UUID id;
    private String nome;
    private String email;
    @Enumerated(EnumType.STRING)
    private Role role;
}
