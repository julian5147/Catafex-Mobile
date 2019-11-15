package com.example.Model;
import com.example.Entities.Cata;
import com.example.Entities.Catacion;
import com.example.Entities.Catas;

import org.json.JSONObject;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CataService {
    private String BASE_URL = "http://192.168.137.69:51316/api/ApiRegistrarCata";
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
            restTemplate.postForEntity(BASE_URL+"/registrarCata" , entity, null);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public List<Catacion> consultarCataciones(String codCatador){
        try {
            return restTemplate.exchange(
                    BASE_URL + "/" + codCatador,
                    HttpMethod.GET, null,
                    new ParameterizedTypeReference<List<Catacion>>() {
                    }
            ).getBody();
        }catch (Exception e){
            return null;
        }
    }

    public boolean updateCatacion(Catacion catacion) {
        try {
            Map<String, String> values = new HashMap<String, String>();
            values.put("codCatacion", catacion.getCodCatacion());
            values.put("cantidad", String.valueOf(catacion.getCantidad()));
            values.put("codCafe", catacion.getCodCafe());
            values.put("codPanel", catacion.getCodPanel());
            values.put("codCatador", catacion.getCodCatador());
            JSONObject jsonObject = new JSONObject(values);
            System.out.println("---"+jsonObject);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<String>(jsonObject.toString(), headers);
            restTemplate.put(BASE_URL +"/actualizarCatacion", entity);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Catas obtenerCata(String codCatacion) {
        try {
            return restTemplate.exchange(
                    BASE_URL + "/ObtenerInformacionCatacion/" + codCatacion,
                    HttpMethod.GET, null,
                    new ParameterizedTypeReference<Catas>() {
                    }
            ).getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
