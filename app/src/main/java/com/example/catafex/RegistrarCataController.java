package com.example.catafex;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


import com.example.Models.Cata;
import com.example.Remote.APIUtils;
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

        regitrar=(Button)findViewById(R.id.buttonRegistrar);

        cataService = APIUtils.getCataService();

        obtenerSeekBar();

        regitrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cata cata = new Cata("CA-1", seekBarsAtributos.get(0).getProgress(),
                        seekBarsAtributos.get(1).getProgress(),seekBarsAtributos.get(2).getProgress(),
                        -1,seekBarsAtributos.get(3).getProgress(),
                        seekBarsAtributos.get(4).getProgress(),seekBarsAtributos.get(5).getProgress(),
                        seekBarsAtributos.get(6).getProgress(),seekBarsAtributos.get(7).getProgress(),
                        "excelente");
                registrarCata(cata);

            }
        });
    }
    public void obtenerSeekBar () {
        for(int i=0; i<seekBarsAtributos.size();i++){

            numAtributos.get(i).setText(""+seekBarsAtributos.get(i).getProgress());
        }
        for (int j=0; j<seekBarsAtributos.size();j++) {
            final int cont=j;
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

    public void registrarCata(Cata cata){
        Call<Boolean> call = cataService.registrarCata(cata);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if(response.isSuccessful()){
                    Toast.makeText(RegistrarCataController.this, "Cata Registrada",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
}


