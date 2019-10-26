package com.example.catafex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.Entities.Catador;
import com.example.Model.CatadorService;

import java.util.concurrent.ExecutionException;

public class Principal extends AppCompatActivity {

    private Button buttonRegistrarCatador;
    private Button buttonIngresar;
    private EditText editTextCedula;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        buttonRegistrarCatador = (Button) findViewById(R.id.buttonRegistrarCatador);
        editTextCedula = (EditText) findViewById(R.id.editTextIngresar);

        buttonRegistrarCatador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Principal.this, RegistrarCatador.class);
                startActivity(intent);
            }
        });

        buttonIngresar = (Button) findViewById(R.id.buttonIngresar);
        buttonIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Catador catador = new HttpResquestGet().execute(editTextCedula.getText().toString()).get();
                    if(catador !=null){
                        Intent intent = new Intent(Principal.this,PerfilCatador.class);
                        intent.putExtra("catador" , catador);
                        startActivity(intent);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private class HttpResquestGet extends AsyncTask<String,Void, Catador>{

        @Override
        protected Catador doInBackground(String... strings) {
            CatadorService catadorService = new CatadorService();
            return catadorService.obtenerCatador(strings[0]);
        }

        @Override
        protected void onPostExecute(Catador catador) {
            super.onPostExecute(catador);
        }
    }
}
