package com.example.projeto_integrador.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "imagens")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Imagem {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String url;

    @ManyToOne
    @JoinColumn(name = "denuncia_id", nullable = false)
    private Denuncia denuncia;
}
