package com.example.catafex.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.Entities.Catador;
import com.example.catafex.R;

/**
 * Clase que me permite visualizar la información del catador en la pagina principal o Home
 */
public class HomeFragment extends Fragment {


    private TextView textViewNombre;
    private TextView textViewCedula;
    private TextView textViewCorreo;
    private TextView textViewCodigo;
    private TextView textViewNivelExp;
    private Catador catador;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        if(getArguments()!=null) {
            catador = (Catador) getArguments().getSerializable("catador");
            textViewNombre = (TextView) root.findViewById(R.id.ViewNombreCatador);
            textViewNombre.setText(catador.getNombre());
            textViewCedula = (TextView) root.findViewById(R.id.ViewCedulaCatador);
            textViewCedula.setText(catador.getCedula());
            textViewCorreo = (TextView) root.findViewById(R.id.ViewCorreoCatador);
            textViewCorreo.setText(catador.getCorreo());
            textViewCodigo = (TextView) root.findViewById(R.id.ViewCodigoCatador);
            textViewCodigo.setText(catador.getCodigo());
            textViewNivelExp = (TextView) root.findViewById(R.id.ViewNivelExpCatador);
            textViewNivelExp.setText(catador.getNivelExp());
        }

        return root;
    }


}