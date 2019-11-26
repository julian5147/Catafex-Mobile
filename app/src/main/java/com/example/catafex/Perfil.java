package com.example.catafex;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.example.Entities.Catador;
import com.example.Model.CatadorService;
import com.example.catafex.ui.misCatas.MisCatasFragment;
import com.example.catafex.ui.home.HomeFragment;

import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

public class Perfil extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private Catador catador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        Intent intent = getIntent();
        catador = (Catador) intent.getSerializableExtra("catador");
        TextView nombre =  headerView.findViewById(R.id.nombre);
        nombre.setText(catador.getNombre());
        TextView correo =  headerView.findViewById(R.id.correo);
        correo.setText(catador.getCorreo());
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                if (destination.getId() == R.id.nav_home) {
                    HomeFragment homeFragment = new HomeFragment();
                    arguments = new Bundle();
                    arguments.putSerializable("catador", catador);
                    homeFragment.setArguments(arguments);
                    getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, homeFragment, null).commit();
                } else if (destination.getId() == R.id.nav_gallery) {
                    MisCatasFragment misCatasFragment = new MisCatasFragment();
                    arguments = new Bundle();
                    arguments.putSerializable("catador", catador);
                    misCatasFragment.setArguments(arguments);
                    getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, misCatasFragment, null).commit();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.perfil, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_actualizar:
                Intent intent = new Intent(Perfil.this, actualizarCatador.class);
                intent.putExtra("catador", catador);
                startActivity(intent);
                finish();
                break;
            case R.id.action_eliminar:
                try {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Perfil.this);
                    builder.setCancelable(false);
                    builder.setTitle("Confirmar");
                    builder.setMessage("¿Estas Seguro?");
                    builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            try {
                                boolean result = new HttpResquestDelete().execute(catador.getCedula()).get();
                                if (result) {
                                    Intent intent = new Intent(Perfil.this, Principal.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(Perfil.this, "Eliminar Cuenta Failed", Toast.LENGTH_SHORT).show();
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
                break;
            case R.id.action_cerrar:
                Intent intent2 = new Intent(Perfil.this, Principal.class);
                startActivity(intent2);
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private static class HttpResquestDelete extends AsyncTask<String, Void, Boolean> {

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
