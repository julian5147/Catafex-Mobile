package com.example.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import com.example.catafex.R;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * clase que me permite adaptar en un ListView los atributos dependiendo cada tipo de café
 */
public class RegistrarCataAdapter extends ArrayAdapter<String> {

    private List<String> atributos;
    private Context context;

    public RegistrarCataAdapter(Context context, List<String> atributos) {
        super(context, R.layout.registrar_cata_layout, atributos);
        this.atributos = atributos;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(final int position, @NonNull View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.registrar_cata_layout, parent, false);
        String atributo = this.atributos.get(position);
        TextView textViewAtributo =  view.findViewById(R.id.textViewAtributo);
        textViewAtributo.setText(atributo);
        return view;
    }
}
