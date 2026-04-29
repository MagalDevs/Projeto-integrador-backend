package com.example.projeto_integrador.controller;

import com.example.projeto_integrador.domain.Denuncia;
import com.example.projeto_integrador.domain.enums.StatusEnum;
import com.example.projeto_integrador.dto.DenunciaRequestCreateDto;
import com.example.projeto_integrador.dto.DenunciaResponseDto;
import com.example.projeto_integrador.service.DenunciaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/denuncias")
public class DenunciaController {
    private final DenunciaService service;

    public DenunciaController(DenunciaService service) {
        this.service = service;
    }

    @GetMapping("/admin")
    public ResponseEntity<List<DenunciaResponseDto>> getAllDenuncias() {
        return ResponseEntity.ok(service.getAllDenuncias().stream().map(denuncia -> new DenunciaResponseDto(denuncia)).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DenunciaResponseDto> getDenunciaById(@PathVariable String id) {
        return ResponseEntity.ok(new DenunciaResponseDto(service.getDenunciaById(UUID.fromString(id))));
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<DenunciaResponseDto>> getDenunciasByUsuarioId(@PathVariable String usuarioId) {
        return ResponseEntity.ok(service.getDenunciasByUsuarioId(UUID.fromString(usuarioId)).stream().map(denuncia -> new DenunciaResponseDto(denuncia)).toList());
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Denuncia> criar(
            @RequestParam("titulo") String titulo,
            @RequestParam("descricao") String descricao,
            @RequestParam("latitude") String latitude,
            @RequestParam("longitude") String longitude,
            @RequestParam("usuarioId") UUID usuarioId,
            @RequestParam("categoriaId") UUID categoriaId,
            @RequestPart(value = "imagens", required = false) List<MultipartFile> imagens
    ) throws IOException {

        DenunciaRequestCreateDto dto = new DenunciaRequestCreateDto(
                titulo, descricao, Float.parseFloat(latitude), Float.parseFloat(longitude), usuarioId, categoriaId, imagens
        );

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.createDenuncia(dto));
    }

    @PatchMapping("{id}/update-status/{status}")
    public ResponseEntity<DenunciaResponseDto> updateStatus(@PathVariable String id, @PathVariable String status) {
        return ResponseEntity.ok(new DenunciaResponseDto(service.updateStatus(UUID.fromString(id), StatusEnum.valueOf(status))));
    }
}
