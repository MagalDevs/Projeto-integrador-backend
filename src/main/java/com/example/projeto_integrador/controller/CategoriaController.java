package com.example.projeto_integrador.controller;

import com.example.projeto_integrador.domain.Categoria;
import com.example.projeto_integrador.dto.CategoriaRequestCreateDto;
import com.example.projeto_integrador.service.CategoriaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
    private final CategoriaService service;

    public CategoriaController(CategoriaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Categoria>> getAllCategories(){
        return ResponseEntity.ok(service.getAllCategories());
    }

    @PostMapping("/admin")
    public ResponseEntity<Categoria> createCategory(@RequestBody CategoriaRequestCreateDto categoria) {
        return ResponseEntity.ok(service.createCategory(categoria));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Categoria> updateCategory(@RequestBody CategoriaRequestCreateDto categoria, @PathVariable String id) {
        return ResponseEntity.ok(service.updateCategoria(categoria, java.util.UUID.fromString(id)));
    }
}
