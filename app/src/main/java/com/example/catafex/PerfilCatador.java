package com.example.catafex;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Entities.Catador;
import com.example.Model.CatadorService;

public class PerfilCatador extends AppCompatActivity {

    private TextView textViewNombre;
    private TextView textViewCedula;
    private TextView textViewCorreo;
    private TextView textViewCodigo;
    private TextView textViewNivelExp;
    private Catador catador;
    private Button buttonEdit;
    private Button buttonDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_catador);

        Intent intent = getIntent();
        catador = (Catador) intent.getSerializableExtra("catador");
        textViewNombre = (TextView) findViewById(R.id.ViewNombre);
        textViewNombre.setText(catador.getNombre());
        textViewCedula = (TextView) findViewById(R.id.ViewCedula);
        textViewCedula.setText(catador.getCedula());
        textViewCorreo = (TextView) findViewById(R.id.ViewCorreo);
        textViewCorreo.setText(catador.getCorreo());
        textViewCodigo = (TextView) findViewById(R.id.ViewCodigo);
        textViewCodigo.setText(catador.getCodigo());
        textViewNivelExp = (TextView) findViewById(R.id.ViewNivelExp);
        textViewNivelExp.setText(catador.getNivelExp());

        buttonEdit = (Button) findViewById(R.id.buttonEditar);

        buttonDelete = (Button) findViewById(R.id.buttonDelete);

        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PerfilCatador.this,actualizarCatador.class);
                intent.putExtra("catador", catador);
                startActivity(intent);
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setCancelable(false);
                    builder.setTitle("Confirmar");
                    builder.setMessage("¿Estas Seguro?");
                    builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            try {
                                boolean result = new HttpResquestDelete().execute(catador.getCedula()).get();
                                if (result) {
                                    Intent intent = new Intent(PerfilCatador.this, Principal.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(PerfilCatador.this, "Eliminar Cuenta Failed", Toast.LENGTH_SHORT).show();
                                }
                            } catch (Exception e) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                                builder.setMessage("Failed");
                            }
                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
                    builder.create().show();
                } catch (Exception e) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                    builder.setMessage("Failed");
                }
            }
        });
    }

    private class HttpResquestDelete extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... strings) {
            CatadorService catadorService = new CatadorService();
            return catadorService.deleteCatador(strings[0]);
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
        }
    }
}
