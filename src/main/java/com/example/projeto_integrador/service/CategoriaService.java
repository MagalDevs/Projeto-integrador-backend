package com.example.projeto_integrador.service;

import com.example.projeto_integrador.domain.Categoria;
import com.example.projeto_integrador.dto.CategoriaRequestCreateDto;
import com.example.projeto_integrador.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoriaService {
    private final CategoriaRepository repository;

    public CategoriaService(CategoriaRepository repository) {
        this.repository = repository;
    }

    public List<Categoria> getAllCategories() {
        return repository.findAll();
    }

    public Categoria getCategoryById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada!"));
    }

    public Categoria createCategory(CategoriaRequestCreateDto categoria) {
        return repository.save(new Categoria(categoria));
    }
}
