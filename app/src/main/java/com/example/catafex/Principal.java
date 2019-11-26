package com.example.catafex;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.Entities.Catador;
import com.example.Model.AutenticarService;


public class Principal extends AppCompatActivity {

    private EditText editTextCorreo;
    private EditText editTextContraseña;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        Button buttonRegistrarCatador = findViewById(R.id.buttonRegistrarCatador);
        editTextCorreo = findViewById(R.id.editTextUsuario);
        editTextContraseña = findViewById(R.id.editTextContrasena);

        buttonRegistrarCatador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Principal.this, RegistrarCatador.class);
                startActivity(intent);
            }
        });

        Button buttonIngresar = findViewById(R.id.buttonIngresar);
        buttonIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Catador catador = new HttpResquestAutenticar().execute(editTextCorreo.getText().toString(), editTextContraseña.getText().toString()).get();
                    if (catador != null) {
                        Intent intent = new Intent(Principal.this, Perfil.class);
                        intent.putExtra("catador", catador);
                        startActivity(intent);
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(Principal.this);
                        builder.setCancelable(false);
                        builder.setTitle("Datos erroneos o Cuenta inactiva");
                        builder.setMessage("Intente nuevamente o comuniquese con su administrador");
                        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                        builder.create().show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private static class HttpResquestAutenticar extends AsyncTask<String, Void, Catador> {

        @Override
        protected Catador doInBackground(String... strings) {
            AutenticarService autenticarService = new AutenticarService();
            return autenticarService.Autenticar(strings[0], strings[1]);
        }

        @Override
        protected void onPostExecute(Catador catador) {
            super.onPostExecute(catador);
        }
    }
}
