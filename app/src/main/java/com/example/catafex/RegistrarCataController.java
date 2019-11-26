package com.example.catafex;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

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
import com.example.Entities.Catas;
import com.example.Model.CataService;
import com.example.adapters.RegistrarCataAdapter;

import java.util.ArrayList;
import java.util.List;

public class RegistrarCataController extends AppCompatActivity {

    private List<SeekBar> seekBarsAtributos;
    private List<TextView> numAtributos;
    private EditText editTextObservaciones;
    private Catas catas;
    private Catador catador;
    private Catacion catacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.registrar_cata);
            seekBarsAtributos = new ArrayList<>();
            numAtributos = new ArrayList<>();
            Intent intent = getIntent();
            catas = (Catas) intent.getSerializableExtra("cata");
            catador = (Catador) intent.getSerializableExtra("catador");
            catacion = (Catacion) intent.getSerializableExtra("catacion");
            ListView listViewRegistrarCata = findViewById(R.id.listViewRegistrarCata);
            listViewRegistrarCata.setItemsCanFocus(true);
            RegistrarCataAdapter registrarCataAdapter = new RegistrarCataAdapter(RegistrarCataController.this, catas.getAtributos());
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
            numAtributos.add((TextView) findViewById(R.id.num1));
            numAtributos.add((TextView) findViewById(R.id.num8));

            editTextObservaciones = findViewById(R.id.editTextObservaciones);

            Button regitrar = findViewById(R.id.buttonRegistrar);
            hacerVisible(catas.getAtributos());
            obtenerSeekBar();

            regitrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        Boolean result = new HttpRequestAdd().execute(obtenerValoresCata(catas.getAtributos())).get();
                        if (result) {
                            Toast.makeText(RegistrarCataController.this, "Cata # " + (catas.getVezCatada() - (catacion.getCantidad() - 1)) + " Registrada - faltan " + (catacion.getCantidad() - 1) + " catas", Toast.LENGTH_SHORT).show();
                            //catas.setVezCatada(catas.getVezCatada() - 1);
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
                                    intent1.putExtra("cata", catas);
                                    intent1.putExtra("catador", catador);
                                    intent1.putExtra("catacion", catacion);
                                    startActivity(intent1);
                                    finish();
                                }
                            } else {
                                Toast.makeText(RegistrarCataController.this, "Failed", Toast.LENGTH_SHORT).show();
                            }
                        } else {
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

    private void hacerVisible(List<String> atributos) {
        for (int i = 0; i < atributos.size(); i++) {
            seekBarsAtributos.get(i).setVisibility(View.VISIBLE);
            numAtributos.get(i).setVisibility(View.VISIBLE);
        }
    }

    private Cata obtenerValoresCata(List<String> atributos) {
        Cata cata = new Cata();
        cata.setCodCata(catacion.getCodCatacion());
        cata.setObservaciones(editTextObservaciones.getText().toString());
        for (int i = 0; i < atributos.size(); i++) {
            switch (atributos.get(i)) {
                case "AROMA":
                    cata.setAroma(seekBarsAtributos.get(i).getProgress());
                    break;
                case "FRAGANCIA":
                    cata.setFragancia(seekBarsAtributos.get(i).getProgress());
                    break;
                case "ACIDEZ":
                    cata.setAcidez(seekBarsAtributos.get(i).getProgress());
                    break;
                case "AMARGO":
                    cata.setAmargo(seekBarsAtributos.get(i).getProgress());
                    break;
                case "CUERPO":
                    cata.setCuerpo(seekBarsAtributos.get(i).getProgress());
                    break;
                case "RANCIDEZ":
                    cata.setRancidez(seekBarsAtributos.get(i).getProgress());
                    break;
                case "SABOR_RESIDUAL":
                    cata.setSaborResidual(seekBarsAtributos.get(i).getProgress());
                    break;
                case "IMPRESION_GLOBAL":
                    cata.setImpresionGlobal(seekBarsAtributos.get(i).getProgress());
                    break;
                case "DULCE":
                    cata.setDulce(seekBarsAtributos.get(i).getProgress());
                    break;
            }

        }
        return cata;
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
}


