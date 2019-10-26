package com.example.catafex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.Entities.Catacion;
import com.example.Entities.Catador;
import com.example.Entities.Catas;
import com.example.Entities.Panel;
import com.example.Model.CataService;
import com.example.Model.PanelService;
import com.example.adapters.MisCatasAdapter;

import java.util.ArrayList;
import java.util.List;

public class MisCatas extends AppCompatActivity {

    private ListView listViewMisCatas;
    private List<Catas> misCatas;
    private Catador catador;
    private Catas cata;
    List<Catacion> cataciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_mis_catas);
            cata = new Catas();
            misCatas = new ArrayList<Catas>();
            final Intent intent = getIntent();
            catador = (Catador) intent.getSerializableExtra("catador");
            listViewMisCatas = (ListView) findViewById(R.id.listViewMisCatas);
            cataciones = new HttpResquestGetCataciones().execute(catador.getCodigo()).get();
            if (cataciones != null) {
                for (Catacion c : cataciones) {
                    if (c.getCantidad() > 0) {
                        Panel panel = new HttpResquestGetPanel().execute(c.getCodPanel()).get();
                        if (panel != null) {
                            cata.setTipoCafe(panel.getTipoCafe());
                            cata.setHora(panel.getHora());
                            cata.setVeces(c.getCantidad());
                            cata.setCodCatacion(c.getCodCatacion());
                            misCatas.add(cata);
                        } else {
                            Toast.makeText(MisCatas.this, "Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(MisCatas.this, "No hay Catas Asignadas", Toast.LENGTH_SHORT).show();
                    }
                }
            } else {
                Toast.makeText(MisCatas.this, "No hay Catas Asignadas", Toast.LENGTH_SHORT).show();
            }
            if (misCatas != null) {
                listViewMisCatas.setAdapter(new MisCatasAdapter(getApplicationContext(), misCatas));
            }
            else{
                Toast.makeText(MisCatas.this, "No hay Catas Asignadas", Toast.LENGTH_SHORT).show();
            }
            listViewMisCatas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    cata = misCatas.get(i);
                    Catacion catacion= cataciones.get(i);
                    Intent intent1 = new Intent(MisCatas.this, RegistrarCataController.class);
                    intent1.putExtra("cata", cata);
                    intent1.putExtra("catador", catador);
                    intent1.putExtra("catacion", catacion);
                    startActivity(intent1);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class HttpResquestGetCataciones extends AsyncTask<String, Void, List<Catacion>> {

        @Override
        protected List<Catacion> doInBackground(String... strings) {
            CataService cataService = new CataService();
            return cataService.consultarCataciones(strings[0]);
        }

        @Override
        protected void onPostExecute(List<Catacion> catacions) {
            super.onPostExecute(catacions);
        }
    }

    private class HttpResquestGetPanel extends AsyncTask<String, Void, Panel> {

        @Override
        protected Panel doInBackground(String... strings) {
            PanelService panelService = new PanelService();
            return panelService.obtenerPanel(strings[0]);
        }

        @Override
        protected void onPostExecute(Panel panel) {
            super.onPostExecute(panel);
        }
    }
}
