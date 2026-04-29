package com.example.projeto_integrador.domain.enums;

public enum StatusEnum {
    ABERTA("Aberta", 1),
    EM_ANALISE("Em análise", 2),
    RESOLVIDA("Resolvida", 3);

    private final String descricao;
    private final int codigo;

    StatusEnum(String descricao, int codigo) {
        this.descricao = descricao;
        this.codigo = codigo;
    }
}
