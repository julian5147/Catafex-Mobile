package com.example.catafex;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


import com.example.Entities.Cata;
import com.example.Entities.Catacion;
import com.example.Entities.Catador;
import com.example.Entities.CatasPendientes;
import com.example.Model.CataService;
import com.example.adapters.RegistrarCataAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Clase que me permite construir y controlar la interfaz de usuario, además de consumir de forma
 * asincrona los servicios pertinentes para registar una cata
 */
public class RegistrarCataController extends AppCompatActivity {

    private List<SeekBar> seekBarsAtributos;
    private List<TextView> numAtributos;
    private EditText editTextObservaciones;
    private CatasPendientes catasPendientes;
    private Catador catador;
    private Catacion catacion;
    private HashMap<String, SeekBar> atributosActuales;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.registrar_cata);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            seekBarsAtributos = new ArrayList<>();
            numAtributos = new ArrayList<>();
            Intent intent = getIntent();
            catasPendientes = (CatasPendientes) intent.getSerializableExtra("cata");
            catador = (Catador) intent.getSerializableExtra("catador");
            catacion = (Catacion) intent.getSerializableExtra("catacion");
            ListView listViewRegistrarCata = findViewById(R.id.listViewRegistrarCata);
            listViewRegistrarCata.setItemsCanFocus(true);
            RegistrarCataAdapter registrarCataAdapter = new RegistrarCataAdapter(RegistrarCataController.this, catasPendientes.getAtributos());
            listViewRegistrarCata.setAdapter(registrarCataAdapter);
            seekBarsAtributos.add((SeekBar) findViewById(R.id.seekBar1));
            seekBarsAtributos.add((SeekBar) findViewById(R.id.seekBar2));
            seekBarsAtributos.add((SeekBar) findViewById(R.id.seekBar3));
            seekBarsAtributos.add((SeekBar) findViewById(R.id.seekBar4));
            seekBarsAtributos.add((SeekBar) findViewById(R.id.seekBar5));
            seekBarsAtributos.add((SeekBar) findViewById(R.id.seekBar6));
            seekBarsAtributos.add((SeekBar) findViewById(R.id.seekBar7));
            seekBarsAtributos.add((SeekBar) findViewById(R.id.seekBar8));

            numAtributos.add((TextView) findViewById(R.id.num1));
            numAtributos.add((TextView) findViewById(R.id.num2));
            numAtributos.add((TextView) findViewById(R.id.num3));
            numAtributos.add((TextView) findViewById(R.id.num4));
            numAtributos.add((TextView) findViewById(R.id.num5));
            numAtributos.add((TextView) findViewById(R.id.num6));
            numAtributos.add((TextView) findViewById(R.id.num7));
            numAtributos.add((TextView) findViewById(R.id.num8));

            atributosActuales = new HashMap<>();

            editTextObservaciones = findViewById(R.id.editTextObservaciones);

            Button registrar = findViewById(R.id.buttonRegistrar);
            hacerVisible(catasPendientes.getAtributos(),catasPendientes.getValoresDefecto());
            obtenerSeekBar();

            registrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        Boolean result = new HttpRequestAdd().execute(obtenerValoresCata()).get();
                        if (result) {
                            Toast.makeText(RegistrarCataController.this, "Cata # " + (catasPendientes.getVezCatada() - (catacion.getCantidad() - 1)) + " Registrada - faltan " + (catacion.getCantidad() - 1) + " catasPendientes", Toast.LENGTH_LONG).show();
                            //catasPendientes.setVezCatada(catasPendientes.getVezCatada() - 1);
                            catacion.setCantidad(catacion.getCantidad() - 1);
                            Boolean resultado = new HttpResquestUpdateCatacion().execute(catacion).get();
                            if (resultado) {
                                if (catacion.getCantidad() == 0) {
                                    Intent intent2 = new Intent(RegistrarCataController.this, Perfil.class);
                                    intent2.putExtra("catador", catador);
                                    startActivity(intent2);
                                    finish();
                                } else {
                                    Intent intent1 = new Intent(RegistrarCataController.this, RegistrarCataController.class);
                                    intent1.putExtra("cata", catasPendientes);
                                    intent1.putExtra("catador", catador);
                                    intent1.putExtra("catacion", catacion);
                                    startActivity(intent1);
                                    finish();
                                }
                            } else {
                                Toast.makeText(RegistrarCataController.this, "Failed", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            System.out.println("print2");
                            Toast.makeText(RegistrarCataController.this, "Failed", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
            builder.setMessage("Failed");
        }
    }

    /**
     * permite obtener los valores del seekBar para ser visualizados en los recuadros que están en
     * al frente de cada seekBar
     */
    private void obtenerSeekBar() {
        for (int i = 0; i < seekBarsAtributos.size(); i++) {
            numAtributos.get(i).setText("" + seekBarsAtributos.get(i).getProgress());


        }
        for (int j = 0; j < seekBarsAtributos.size(); j++) {


            final int cont = j;
            seekBarsAtributos.get(j).setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                    numAtributos.get(cont).setText("" + i);
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });

        }

    }

    private void borrarAtributosActuales() {

        atributosActuales.clear();
    }

    /**
     * se encarga de hacer visible los los seekBar y recuadros de pendiendo de el número de
     * atributos que tiene el café a catar
     * @param atributos Lista de atributos que tiene el tipo de café a catar
     */
    private void hacerVisible(List<String> atributos, List<String> valoresDefecto) {

        borrarAtributosActuales();

        for (int i = 0; i < atributos.size(); i++) {
            atributosActuales.put(atributos.get(i).toUpperCase(), seekBarsAtributos.get(i));
            seekBarsAtributos.get(i).setVisibility(View.VISIBLE);
            seekBarsAtributos.get(i).setProgress(Integer.parseInt(valoresDefecto.get(i)));
            numAtributos.get(i).setVisibility(View.VISIBLE);
        }
    }

    /**
     * permite asignarle a cada atributo de la cata el progreso de su seekBar correspondiente
     * @return un objeto cata con lo valores catados
     */
    private Cata obtenerValoresCata() {
        Cata cata = new Cata();
        cata.setCodCata(catacion.getCodCatacion());
        cata.setObservaciones(editTextObservaciones.getText().toString());

        cata.setAroma(obtenerProgresoSeekBar("AROMA"));
        cata.setFragancia(obtenerProgresoSeekBar("FRAGANCIA"));
        cata.setAcidez(obtenerProgresoSeekBar("ACIDEZ"));
        cata.setAmargo(obtenerProgresoSeekBar("AMARGO"));
        cata.setCuerpo(obtenerProgresoSeekBar("CUERPO"));
        cata.setRancidez(obtenerProgresoSeekBar("RANCIDEZ"));
        cata.setSaborResidual(obtenerProgresoSeekBar("SABOR_RESIDUAL"));
        cata.setImpresionGlobal(obtenerProgresoSeekBar("IMPRESION_GLOBAL"));
        cata.setDulce(obtenerProgresoSeekBar("DULCE"));

        return cata;
    }


    /**
     * obtiene el progreso de cada seekBar dependiendo del atributo al que corresponda
     * @param nombreAtributo del tipo de cáfe del cual se desea su valor catado
     * @return valor del seekBar en su correspondiente atributo
     */
    private int obtenerProgresoSeekBar(String nombreAtributo) {

        if (!atributosActuales.containsKey(nombreAtributo))
            return 0;
        else
            return atributosActuales.get(nombreAtributo).getProgress();

    }

    private static class HttpRequestAdd extends AsyncTask<Cata, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Cata... catas) {
            CataService cataService = new CataService();
            return cataService.registrar(catas[0]);
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
        }
    }

    private static class HttpResquestUpdateCatacion extends AsyncTask<Catacion, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Catacion... catacions) {
            CataService cataService = new CataService();
            return cataService.updateCatacion(catacions[0]);
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent intent2 = new Intent(RegistrarCataController.this, Perfil.class);
            intent2.putExtra("catador", catador);
            startActivity(intent2);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}


