package com.example.Model;

import com.example.Entities.Catador;

import org.json.JSONObject;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Clase que permite consumir los servicios necesarios para gestionar un catador
 */
public class CatadorService {
    private final String BASE_URL = "https://webapicatafex.azurewebsites.net/api/ApiRegistrarCatador";
    private final RestTemplate restTemplate = new RestTemplate();

    /**
     * registrar un catador con los valores prevaimente ingresados
     *
     * @param catador
     * @return true si el catador fue regsitrado exitosamente o false si no fue registrado o ocurrio
     * un error en el servicio
     */
    public HttpStatus registrarCatador(Catador catador) {
            try{Map<String, String> values = new HashMap<>();
            values.put("nombre", catador.getNombre());
            values.put("cedula", catador.getCedula());
            values.put("correo", catador.getCorreo());
            values.put("contrasena", catador.getContrasena());
            values.put("nivelExp", catador.getNivelExp());
            values.put("codigo", catador.getCodigo());
            JSONObject jsonObject = new JSONObject(values);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<>(jsonObject.toString(), headers);
            ResponseEntity response = restTemplate.postForEntity(BASE_URL, entity, null);
            return response.getStatusCode();
            }catch (HttpStatusCodeException e){
                return e.getStatusCode();
            }
    }

    /**
     * permite obtener un catador con el número de su cedula
     *
     * @param cedula del Catador
     * @return un objeto Catador si su cédula es correcta
     */
    public Catador obtenerCatador(String cedula) {
        try {
            return restTemplate.exchange(
                    BASE_URL + "?cedula=" + cedula,
                    HttpMethod.GET, null,
                    new ParameterizedTypeReference<Catador>() {
                    }
            ).getBody();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * permite eliminar un catador de la base de datos
     *
     * @param cedula
     * @return true si el Catador fue eliminado exitosamente, false si no fue eliminado o ocurrio
     * algún error con el servicio
     */
    public boolean deleteCatador(String cedula) {
        try {
            restTemplate.delete(BASE_URL + "?cedula=" + cedula);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    /**
     * permite actualizar el nombre, correo y contraseña de un Catador dado su cedula
     *
     * @param catador objeto Catador creado con los valores a actualizar
     * @return true si el Catador fue actualizado exitosamente, false si el catador no fue
     * actualizado o ocurrio  un error en el servicio.
     */
    public boolean updateCatador(Catador catador) {
        try {
            Map<String, String> values = new HashMap<>();
            values.put("nombre", catador.getNombre());
            values.put("correo", catador.getCorreo());
            values.put("contrasena", catador.getContrasena());
            values.put("cedula", catador.getCedula());
            JSONObject jsonObject = new JSONObject(values);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<>(jsonObject.toString(), headers);
            restTemplate.put(BASE_URL, entity);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
