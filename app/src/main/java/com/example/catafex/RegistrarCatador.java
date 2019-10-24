package com.example.catafex;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.Models.Catador;
import com.example.Remote.CatadorService;

import java.util.ArrayList;
import java.util.List;

public class RegistrarCatador extends AppCompatActivity {

    private Spinner spinnerNivelExperiencia;
    List<String> niveles = new ArrayList<String>();

    private EditText editTextName;
    private EditText editTextCedula;
    private EditText editTextCorreo;
    private EditText editTextContraseña;
    private EditText editTextCodigo;

    private Button buttonGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_catador);

        spinnerNivelExperiencia = (Spinner) findViewById(R.id.spinnerNivelExperiencia);
        niveles.add("Experimentado");
        niveles.add("Semi-Experimentado");
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

        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextCedula = (EditText) findViewById(R.id.editTextCedula);
        editTextCorreo = (EditText) findViewById(R.id.editTextCorreo);
        editTextContraseña = (EditText) findViewById(R.id.editTextContraseña);
        editTextCodigo = (EditText) findViewById(R.id.editTextCodigo);

        buttonGuardar = (Button) findViewById(R.id.buttonGuardarCatador);

        buttonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Catador catador = new Catador();
                    catador.setNombre(editTextName.getText().toString());
                    catador.setCedula(editTextCedula.getText().toString());
                    catador.setCorreo(editTextCorreo.getText().toString());
                    catador.setContrasena(editTextContraseña.getText().toString());
                    catador.setNivelExp(spinnerNivelExperiencia.getSelectedItem().toString());
                    catador.setCodigo(editTextCodigo.getText().toString());
                    Boolean result = new HttpRequestAddCatador().execute(catador).get();
                    if (result) {
                        Toast.makeText(RegistrarCatador.this, "Registrado", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(RegistrarCatador.this, "Faild", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                    builder.setMessage("Failed");
                }
            }
        });

    }

    private class HttpRequestAddCatador extends AsyncTask<Catador, Void, Boolean> {


        @Override
        protected Boolean doInBackground(Catador... catadors) {
            CatadorService catadorService = new CatadorService();
            return catadorService.registrarCatador(catadors[0]);
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
        }
    }
}
