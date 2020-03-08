package com.example.catafex;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.Entities.Catador;
import com.example.Model.CatadorService;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que me permite construir y controlar la interfaz de usuario, además de consumir de forma
 * asincrona los servicios pertinentes para registar un catador
 */
public class RegistrarCatador extends AppCompatActivity {

    private Spinner spinnerNivelExperiencia;
    private final List<String> niveles = new ArrayList<>();

    private EditText editTextName;
    private EditText editTextCedula;
    private EditText editTextCorreo;
    private EditText editTextContraseña;
    private EditText editTextCodigo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_catador);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        spinnerNivelExperiencia = (Spinner) findViewById(R.id.spinnerNivelExperiencia);
        niveles.add("EXPERIMENTADO");
        niveles.add("SEMIEXPERIMENTADO");
        niveles.add("Seleccionar");
        final int listsize = niveles.size() - 1;

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, niveles) {
            @Override
            public int getCount() {
                return (listsize);
            }
        };
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerNivelExperiencia.setAdapter(dataAdapter);
        spinnerNivelExperiencia.setSelection(listsize);

        editTextName = findViewById(R.id.editTextName);
        editTextCedula = findViewById(R.id.editTextCedula);
        editTextCorreo = findViewById(R.id.editTextCorreo);
        editTextContraseña = findViewById(R.id.editTextContraseña);
        editTextCodigo = findViewById(R.id.editTextCodigo);

        Button buttonGuardar = findViewById(R.id.buttonGuardarCatador);

        buttonGuardar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                try {
                    HttpStatus result = new HttpRequestAddCatador().execute(obtenerDatosCatador()).get();
                    if (result == HttpStatus.OK) {
                        Toast.makeText(RegistrarCatador.this, "Registrado, espere a ser habilitado para su autenticación", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(RegistrarCatador.this, Principal.class);
                        startActivity(intent);
                        finish();
                    } else if (result == HttpStatus.PRECONDITION_FAILED) {
                        Toast.makeText(RegistrarCatador.this, "Los datos del catador no pueden estar vacios", Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(RegistrarCatador.this, "El catador con la cedula" + obtenerDatosCatador().getCedula()  + "ya esta registrado o ocurrio un error al registrar" + result.toString(), Toast.LENGTH_LONG).show();
                    }
                } catch (Exception r) {
                    Toast.makeText(RegistrarCatador.this, r.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private static class HttpRequestAddCatador extends AsyncTask<Catador, Void, HttpStatus> {


        @Override
        protected HttpStatus doInBackground(Catador... catadors)  {
            CatadorService catadorService = new CatadorService();
            return catadorService.registrarCatador(catadors[0]);
        }

        @Override
        protected void onPostExecute(HttpStatus httpStatus) {
            super.onPostExecute(httpStatus);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Permite obtener los valores ingresados por un catador con sus respectivas validaciones
     * @return un objeto Catador el cual será registrado
     */
    private Catador obtenerDatosCatador() {
        Catador catador = new Catador();
        String nivelExp = spinnerNivelExperiencia.getSelectedItem().toString();
        catador.setNombre(editTextName.getText().toString());
        catador.setCedula(editTextCedula.getText().toString());
        catador.setCorreo(editTextCorreo.getText().toString());
        catador.setContrasena(editTextContraseña.getText().toString());
        if(!nivelExp.equals("Seleccionar")){
            catador.setNivelExp(nivelExp);
        }
        catador.setCodigo(editTextCodigo.getText().toString());
        return catador;
    }
}
