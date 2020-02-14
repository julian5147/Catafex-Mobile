package com.example.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.Entities.CatasPendientes;
import com.example.catafex.R;

import java.util.List;

import androidx.annotation.NonNull;

/**
 * Clase que me permite adaptar en un ListView el objeto CatasPendientes con el fin de que las catas
 * pendientes se liste de forma dinámica al usuario.
 */
public class MisCatasAdapter extends ArrayAdapter<CatasPendientes> {

    private List<CatasPendientes> catas;
    private Context context;

    public MisCatasAdapter(Context context, List<CatasPendientes> catas) {
        super(context, R.layout.miscatas_layout, catas);
        this.catas = catas;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @NonNull View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.miscatas_layout,parent,false);
        CatasPendientes catasPendientes = this.catas.get(position);
        TextView textViewCafe = (TextView) view.findViewById(R.id.textViewCafe);
        textViewCafe.setText(catasPendientes.getTipoCafe());
        TextView textViewHora = (TextView) view.findViewById(R.id.textViewHora);
        textViewHora.setText(catasPendientes.getHora());
        TextView textViewVeces = (TextView) view.findViewById(R.id.textViewVeces);
        textViewVeces.setText(String.valueOf(catasPendientes.getVezCatada()));
        TextView textViewFecha = (TextView) view.findViewById(R.id.textViewFecha);
        textViewFecha.setText(catasPendientes.getFecha());
        TextView textViewCodCafe = (TextView) view.findViewById(R.id.textViewCodCafe);
        textViewCodCafe.setText(catasPendientes.getCodCafe());
        return view;
    }
}
