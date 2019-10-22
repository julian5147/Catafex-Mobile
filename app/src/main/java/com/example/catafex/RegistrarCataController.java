package com.example.catafex;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


import com.example.Models.Cata;
import com.example.Remote.CataService;

import java.util.ArrayList;
import java.util.List;

public class RegistrarCataController extends AppCompatActivity {

    private List<SeekBar> seekBarsAtributos = new ArrayList<SeekBar>();
    private List<TextView> numAtributos = new ArrayList<TextView>();
    private Button regitrar;

    CataService cataService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBarsAtributos.add((SeekBar) findViewById(R.id.seekBarFragancia));
        seekBarsAtributos.add((SeekBar) findViewById(R.id.seekBarAroma));
        seekBarsAtributos.add((SeekBar) findViewById(R.id.seekBarAcidez));
        seekBarsAtributos.add((SeekBar) findViewById(R.id.seekBarAmargo));
        seekBarsAtributos.add((SeekBar) findViewById(R.id.seekBarCuerpo));
        seekBarsAtributos.add((SeekBar) findViewById(R.id.seekBarRancidez));
        seekBarsAtributos.add((SeekBar) findViewById(R.id.seekBarResidual));
        seekBarsAtributos.add((SeekBar) findViewById(R.id.seekBarGlobal));

        numAtributos.add((TextView) findViewById(R.id.numFragancia));
        numAtributos.add((TextView) findViewById(R.id.numAroma));
        numAtributos.add((TextView) findViewById(R.id.numAcidez));
        numAtributos.add((TextView) findViewById(R.id.numAmargo));
        numAtributos.add((TextView) findViewById(R.id.numCuerpo));
        numAtributos.add((TextView) findViewById(R.id.numRancidez));
        numAtributos.add((TextView) findViewById(R.id.numResidual));
        numAtributos.add((TextView) findViewById(R.id.numGlobal));

        regitrar = (Button) findViewById(R.id.buttonRegistrar);


        obtenerSeekBar();

        regitrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Cata cata = new Cata();
                    cata.setCodCatacion("CT-2");
                    cata.setFragancia(seekBarsAtributos.get(0).getProgress());
                    cata.setAroma(seekBarsAtributos.get(1).getProgress());
                    cata.setAcidez(seekBarsAtributos.get(2).getProgress());
                    cata.setAmargo(seekBarsAtributos.get(3).getProgress());
                    cata.setCuerpo(seekBarsAtributos.get(4).getProgress());
                    cata.setRancidez(seekBarsAtributos.get(5).getProgress());
                    cata.setSaborResidual(seekBarsAtributos.get(6).getProgress());
                    cata.setImpresionGlobal(seekBarsAtributos.get(7).getProgress());
                    cata.setDulce(-1);
                    cata.setObservaciones("excelente");
                    Boolean result = new HttpRequestAdd().execute(cata).get();
                    if (result) {
                        Toast.makeText(RegistrarCataController.this, "Cata Registrada", Toast.LENGTH_SHORT).show();
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                        builder.setMessage("Failed");
                        builder.create().show();
                    }
                } catch (Exception e) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                    builder.setMessage("Failed");
                }

            }
        });
    }

    public void obtenerSeekBar() {
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

    private class HttpRequestAdd extends AsyncTask<Cata, Void, Boolean> {

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
}


