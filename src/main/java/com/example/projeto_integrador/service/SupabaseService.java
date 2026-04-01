package com.example.projeto_integrador.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;


@Service
public class SupabaseService {
    private final String PROJECT_ID = "shmnyiwvnykooywizcjv";
    private final String API_URL = "https://"+ PROJECT_ID +".supabase.co/auth/v1/admin/users";

    @Value("${supabase.service_role_key}")
    private String SERVICE_ROLE_KEY;

    public String createAuthUser(String email, String password){
        HttpHeaders headers = new HttpHeaders();
        headers.set("apikey", SERVICE_ROLE_KEY);
        headers.set("Authorization", "Bearer " + SERVICE_ROLE_KEY);
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> body = new HashMap<>();
        body.put("email", email);
        body.put("password", password);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map> response = restTemplate.postForEntity(API_URL, request, Map.class);

        return (String) response.getBody().get("id");
    }
}
