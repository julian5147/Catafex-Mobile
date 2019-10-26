package com.example.catafex;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.Entities.Catador;
import com.example.Model.CatadorService;

public class actualizarCatador extends AppCompatActivity {

    private EditText editTextName;
    private EditText editTextCorreo;
    private EditText editTextContraseña;
    private Button buttonGuardar;
    private Button buttonCancelar;
    private Catador catador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_catador);
        editTextName = (EditText) findViewById(R.id.TextName);
        editTextCorreo = (EditText) findViewById(R.id.TextCorreo);
        editTextContraseña = (EditText) findViewById(R.id.TextContraseña);

        Intent intent = getIntent();
        catador = (Catador) intent.getSerializableExtra("catador");

        editTextName.setText(catador.getNombre());
        editTextCorreo.setText(catador.getCorreo());
        editTextContraseña.setText(catador.getContrasena());

        buttonGuardar = (Button) findViewById(R.id.buttonUpdateCatador);

        buttonCancelar = (Button) findViewById(R.id.buttonCancelar);

        buttonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(actualizarCatador.this, PerfilCatador.class);
                intent.putExtra("catador",catador);
                startActivity(intent);
            }
        });

        buttonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    catador.setNombre(editTextName.getText().toString());
                    catador.setCorreo(editTextCorreo.getText().toString());
                    catador.setContrasena(editTextContraseña.getText().toString());
                    boolean result = new HttpResquestUpdate().execute(catador).get();
                    if(result){
                        Intent intent = new Intent(actualizarCatador.this, PerfilCatador.class);
                        intent.putExtra("catador" , catador);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(actualizarCatador.this, "Actualización Failed", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                    builder.setMessage("Failed");
                }
            }
        });
    }

    private class HttpResquestUpdate extends AsyncTask<Catador, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Catador... catadors) {
            CatadorService catadorService = new CatadorService();
            return catadorService.updateCatador(catadors[0]);
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
        }
    }

}
