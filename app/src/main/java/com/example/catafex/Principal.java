package com.example.catafex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Principal extends AppCompatActivity {

    private Button buttonRegistrarCatador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        buttonRegistrarCatador = (Button) findViewById(R.id.buttonRegistrarCatador);

        buttonRegistrarCatador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Principal.this, RegistrarCatador.class);
                startActivity(intent);
            }
        });
    }
}
