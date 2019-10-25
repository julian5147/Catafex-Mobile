package com.example.Model;
import com.example.Entities.Cata;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class CataService {
    private String BASE_URL = "http://192.168.1.75:51316/api/";
    private RestTemplate restTemplate = new RestTemplate();

    public boolean registrar(Cata cata) {
        try {
            Map<String, String> values = new HashMap<String, String>();
            values.put("codigo", cata.getCodCatacion());
            values.put("fragancia", String.valueOf(cata.getFragancia()));
            values.put("aroma", String.valueOf(cata.getAroma()));
            values.put("acidez", String.valueOf(cata.getAcidez()));
            values.put("amargo", String.valueOf(cata.getAmargo()));
            values.put("dulce", String.valueOf(cata.getDulce()));
            values.put("rancidez", String.valueOf(cata.getRancidez()));
            values.put("cuerpo", String.valueOf(cata.getCuerpo()));
            values.put("saborResidual", String.valueOf(cata.getSaborResidual()));
            values.put("impresionGlobal", String.valueOf(cata.getImpresionGlobal()));
            values.put("observaciones", cata.getObservaciones());
            JSONObject jsonObject = new JSONObject(values);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<String>(jsonObject.toString(), headers);
            restTemplate.postForEntity(BASE_URL + "ApiRegistrarCata", entity, null);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
