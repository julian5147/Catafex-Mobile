package com.example.Remote;
import com.example.Models.Catador;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class CatadorService {
    private String BASE_URL = "http://192.168.1.75:51316/api/";
    private RestTemplate restTemplate = new RestTemplate();

    public boolean registrarCatador(Catador catador) {
        try {
            Map<String, String> values = new HashMap<String, String>();
            values.put("nombre", catador.getNombre());
            values.put("cedula", catador.getCedula());
            values.put("correo", catador.getCorreo());
            values.put("contrasena", catador.getContrasena());
            values.put("nivelExp", catador.getNivelExp());
            values.put("codigo", catador.getCodigo());
            JSONObject jsonObject = new JSONObject(values);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<String>(jsonObject.toString(), headers);
            restTemplate.postForEntity(BASE_URL + "ApiRegistrarCatador", entity, null);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
