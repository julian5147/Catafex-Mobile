package com.example.Model;

import com.example.Entities.Panel;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

public class PanelService {
    private String BASE_URL = "http://192.168.137.190:51316/api/ApiGestionarPanel";
    private RestTemplate restTemplate = new RestTemplate();

    public Panel obtenerPanel(String codPanel) {
        try {
            return restTemplate.exchange(
                    BASE_URL + "?codigo=" + codPanel,
                    HttpMethod.GET, null,
                    new ParameterizedTypeReference<Panel>() {
                    }
            ).getBody();
        } catch (Exception e) {
            return null;
        }
    }
}
