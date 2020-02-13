package com.example.catafex.ui.misCatas;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.Entities.Catacion;
import com.example.Entities.Catador;
import com.example.Entities.Catas;
import com.example.Model.CataService;
import com.example.adapters.MisCatasAdapter;
import com.example.catafex.R;
import com.example.catafex.RegistrarCataController;

import java.util.ArrayList;
import java.util.List;

public class MisCatasFragment extends Fragment {

    private ListView listViewMisCatas;
    private List<Catas> misCatas;
    private Catador catador;
    private Catas cata;
    private List<Catacion> cataciones;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_miscatas, container, false);
        try {
            misCatas = new ArrayList<>();
            if (getArguments() != null) {
                catador = (Catador) getArguments().getSerializable("catador");
                listViewMisCatas = root.findViewById(R.id.listViewMisCatas);
                cataciones = new HttpResquestGetCataciones().execute(catador.getCodigo()).get();
            }
            if (!cataciones.isEmpty()) {
                for (Catacion c : cataciones) {
                    cata = new HttpResquestGetCatas().execute(c.getCodCatacion()).get();
                    if (cata != null) {
                        misCatas.add(cata);
                    } else {
                        Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
                    }
                }
                if (misCatas != null) {
                    listViewMisCatas.setAdapter(new MisCatasAdapter(getContext(), misCatas));
                } else {
                    Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getContext(), "No hay Catas Asignadas", Toast.LENGTH_SHORT).show();
            }
            listViewMisCatas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    cata = misCatas.get(i);
                    Catacion catacion = cataciones.get(i);
                    Intent intent1 = new Intent(getContext(), RegistrarCataController.class);
                    intent1.putExtra("cata", cata);
                    intent1.putExtra("catador", catador);
                    intent1.putExtra("catacion", catacion);
                    startActivity(intent1);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return root;
    }

    private static class HttpResquestGetCataciones extends AsyncTask<String, Void, List<Catacion>> {

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

    private static class HttpResquestGetCatas extends AsyncTask<String, Void, Catas> {

        @Override
        protected Catas doInBackground(String... strings) {
            CataService cataService = new CataService();
            return cataService.obtenerCata(strings[0]);
        }

        @Override
        protected void onPostExecute(Catas catas) {
            super.onPostExecute(catas);
        }
    }
}