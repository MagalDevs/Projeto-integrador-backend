package com.example.projeto_integrador.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class SupabaseStorageService {

    @Value("${supabase.url}")
    private String supabaseUrl;

    @Value("${supabase.key}")
    private String supabaseKey;

    @Value("${supabase.bucket}")
    private String bucket;

    private final RestTemplate restTemplate = new RestTemplate();

    public String uploadImagem(MultipartFile file, String denunciaId) throws IOException {
        // Nome único para evitar colisões
        String fileName = denunciaId + "/" + UUID.randomUUID() + "_" + file.getOriginalFilename();

        String uploadUrl = supabaseUrl + "/storage/v1/object/" + bucket + "/" + fileName;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + supabaseKey);
        headers.setContentType(MediaType.parseMediaType(file.getContentType()));

        HttpEntity<byte[]> entity = new HttpEntity<>(file.getBytes(), headers);

        restTemplate.exchange(uploadUrl, HttpMethod.POST, entity, String.class);

        // Retorna a URL pública
        return supabaseUrl + "/storage/v1/object/public/" + bucket + "/" + fileName;
    }
}
