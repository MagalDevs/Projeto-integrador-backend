package com.example.projeto_integrador.service;

import com.example.projeto_integrador.domain.Denuncia;
import com.example.projeto_integrador.domain.Imagem;
import com.example.projeto_integrador.repository.ImagemRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ImagemService {
    private final ImagemRepository imagemRepository;
    private final SupabaseStorageService storageService;

    public ImagemService(ImagemRepository imagemRepository,
                         SupabaseStorageService storageService) {
        this.imagemRepository = imagemRepository;
        this.storageService = storageService;
    }

    public void salvarImagens(List<MultipartFile> arquivos, Denuncia denuncia) throws IOException {
        for (MultipartFile arquivo : arquivos) {
            String url = storageService.uploadImagem(arquivo, denuncia.getId().toString());

            Imagem imagem = new Imagem();
            imagem.setUrl(url);
            imagem.setDenuncia(denuncia);
            imagemRepository.save(imagem);
        }
    }
}
