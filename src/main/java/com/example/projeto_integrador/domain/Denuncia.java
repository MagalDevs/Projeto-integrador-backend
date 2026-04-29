package com.example.projeto_integrador.domain;

import com.example.projeto_integrador.domain.enums.StatusEnum;
import com.example.projeto_integrador.dto.DenunciaRequestCreateDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "denuncias")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Denuncia {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String titulo;
    private String descricao;
    @Enumerated(EnumType.STRING)
    private StatusEnum status;
    private float latitude;
    private float longitude;
    @CreationTimestamp
    @Column(name = "created_at")
    private Instant createdAt;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

    @OneToMany(mappedBy = "denuncia", fetch = FetchType.LAZY)
    private List<Imagem> imagens = new ArrayList<>();

    public Denuncia(DenunciaRequestCreateDto denuncia, Usuario usuario, Categoria categoria) {
        this.titulo = denuncia.titulo();
        this.descricao = denuncia.descricao();
        this.status = StatusEnum.ABERTA;
        this.latitude = denuncia.latitude();
        this.longitude = denuncia.longitude();
        this.usuario = usuario;
        this.categoria = categoria;
    }
}
