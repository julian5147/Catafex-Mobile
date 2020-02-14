package com.example.Model;
import com.example.Entities.Cata;
import com.example.Entities.Catacion;
import com.example.Entities.CatasPendientes;

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

/**
 * Clase que permite consumir los servicios necesarios para Registrar y consultar catas pendientes
 */
public class CataService {
    private final String BASE_URL = "http://192.168.1.75:51316/api/ApiRegistrarCata";
    private final RestTemplate restTemplate = new RestTemplate();

    /**
     * permite consumir el servicio ApiRegistrarCata con el fin de registrar los valores
     * ingresados de una cata
     * @param cata objeto con todos los valores de la cata ya registrados
     * @return true si la cata fue registrada exitosamente y false si no fue registrada o se produce
     * un error en el servicio
     */
    public boolean registrar(Cata cata) {
        try {
            Map<String, String> values = new HashMap<>();
            values.put("codigo", cata.getCodCata());
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
            HttpEntity<String> entity = new HttpEntity<>(jsonObject.toString(), headers);
            restTemplate.postForEntity(BASE_URL+"/registrarCata" , entity, null);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * permite consumir el servicio para consultar la catas pendientes que tiene asignadas el
     * catador
     * @param codCatador código del catador que me permite buscar que catas tiene asignadas
     * @return una lista con las catas pendientes, si no cuenta con cataciones pendientes me retorna
     * null
     */
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

    /**
     * permite actualizar un catación en este caso se actualiza la cantidad de veces a catar con
     * el fin de dar por finalizada esa catación
     * @param catacion objeto catación con los valores necesarios para actualizar dicha catación
     * @return true si la Catación fue actualizada exitosamente o false si no lo fue actualiza o
     * ocurrio un error en el servicio.
     */
    public boolean updateCatacion(Catacion catacion) {
        try {
            Map<String, String> values = new HashMap<>();
            values.put("codCatacion", catacion.getCodCatacion());
            values.put("cantidad", String.valueOf(catacion.getCantidad()));
            values.put("codCafe", catacion.getCodCafe());
            values.put("codPanel", catacion.getCodPanel());
            values.put("codCatador", catacion.getCodCatador());
            JSONObject jsonObject = new JSONObject(values);
            System.out.println("---"+jsonObject);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<>(jsonObject.toString(), headers);
            restTemplate.put(BASE_URL +"/actualizarCatacion", entity);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * permite consumir el servicio para obtener la información de las catas pendientes
     * @param codCatacion
     * @return un objeto CatasPendientes
     */
    public CatasPendientes obtenerCata(String codCatacion) {
        try {
            return restTemplate.exchange(
                    BASE_URL + "/ObtenerInformacionCatacion/" + codCatacion,
                    HttpMethod.GET, null,
                    new ParameterizedTypeReference<CatasPendientes>() {
                    }
            ).getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
