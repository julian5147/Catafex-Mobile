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

/**
 * Clase que me permite construir y controlar la interfaz de usuario, además de consumir de forma
 * asincrona los servicios pertinentes para actualizar un catador
 */
public class actualizarCatador extends AppCompatActivity {

    private EditText editTextName;
    private EditText editTextCorreo;
    private EditText editTextContraseña;
    private Catador catador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_catador);
        editTextName =  findViewById(R.id.TextName);
        editTextCorreo = findViewById(R.id.TextCorreo);
        editTextContraseña = findViewById(R.id.TextContraseña);

        Intent intent = getIntent();
        catador = (Catador) intent.getSerializableExtra("catador");

        editTextName.setText(catador.getNombre());
        editTextCorreo.setText(catador.getCorreo());

        Button buttonGuardar = findViewById(R.id.buttonUpdateCatador);

        Button buttonCancelar = findViewById(R.id.buttonCancelar);

        buttonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(actualizarCatador.this, Perfil.class);
                intent.putExtra("catador", catador);
                startActivity(intent);
            }
        });

        buttonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Catador catador1 = new Catador();
                    catador1.setNombre(editTextName.getText().toString());
                    catador1.setCorreo(editTextCorreo.getText().toString());
                    System.out.println("-"+editTextContraseña.getText().toString());
                    if (editTextContraseña.getText().toString().equals("")) {
                        catador1.setContrasena(catador.getContrasena());
                    } else {
                        catador1.setContrasena(editTextContraseña.getText().toString());
                    }
                    catador1.setCedula(catador.getCedula());
                    catador1.setCodigo(catador.getCodigo());
                    catador1.setNivelExp(catador.getNivelExp());
                    boolean result = new HttpResquestUpdate().execute(catador1).get();
                    if (result) {
                        Intent intent = new Intent(actualizarCatador.this, Perfil.class);
                        intent.putExtra("catador", catador1);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(actualizarCatador.this, "Actualización Failed", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(actualizarCatador.this);
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
