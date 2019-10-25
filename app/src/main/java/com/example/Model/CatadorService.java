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

public class CatadorService {
    private String BASE_URL = "http://192.168.1.75:51316/api/ApiRegistrarCatador";
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
            restTemplate.postForEntity(BASE_URL, entity, null);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Catador obtenerCatador(String cedula){
        try {
            return restTemplate.exchange(
                    BASE_URL + "?cedula=" + cedula,
                    HttpMethod.GET, null,
                    new ParameterizedTypeReference<Catador>() {
                    }
            ).getBody();
        }catch (Exception e){
            return null;
        }
    }

    public boolean deleteCatador(String cedula){
        try {
            restTemplate.delete(BASE_URL+ "?cedula=" + cedula);
            return true;
        }catch (Exception e){
            return false;
        }

    }

    public boolean updateCatador(Catador catador) {
        try {
            Map<String, String> values = new HashMap<String, String>();
            values.put("nombre", catador.getNombre());
            values.put("correo", catador.getCorreo());
            values.put("contrasena", catador.getContrasena());
            values.put("cedula",catador.getCedula());
            JSONObject jsonObject = new JSONObject(values);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<String>(jsonObject.toString(), headers);
            restTemplate.put(BASE_URL , entity);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
