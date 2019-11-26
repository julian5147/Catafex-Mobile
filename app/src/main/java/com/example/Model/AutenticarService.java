package com.example.Model;

import com.example.Entities.Catador;

import org.json.JSONObject;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class AutenticarService {

    private final RestTemplate restTemplate = new RestTemplate();

    public Catador Autenticar(String correo, String contrasena){
        try {
            Map<String, String> values = new HashMap<>();
            values.put("correo", correo);
            values.put("contrasena", contrasena);
            JSONObject jsonObject = new JSONObject(values);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<>(jsonObject.toString(), headers);
            String BASE_URL = "http://192.168.1.75:51316/api/ApiAutenticar";
            return restTemplate.exchange(
                    BASE_URL, HttpMethod.POST, entity,
                    new ParameterizedTypeReference<Catador>() {
                    }
            ).getBody();
        }catch (Exception e){
            return null;
        }
    }
}
