package com.example.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.Entities.Catas;
import com.example.catafex.R;

import java.util.List;

import androidx.annotation.NonNull;

public class MisCatasAdapter extends ArrayAdapter<Catas> {

    private List<Catas> catas;
    private Context context;

    public MisCatasAdapter(Context context, List<Catas> catas) {
        super(context, R.layout.miscatas_layout, catas);
        this.catas = catas;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @NonNull View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.miscatas_layout,parent,false);
        Catas catas = this.catas.get(position);
        TextView textViewCafe = (TextView) view.findViewById(R.id.textViewCafe);
        textViewCafe.setText(catas.getTipoCafe());
        TextView textViewHora = (TextView) view.findViewById(R.id.textViewHora);
        textViewHora.setText(catas.getHora());
        TextView textViewVeces = (TextView) view.findViewById(R.id.textViewVeces);
        textViewVeces.setText(String.valueOf(catas.getVezCatada()));
        TextView textViewFecha = (TextView) view.findViewById(R.id.textViewFecha);
        textViewFecha.setText(String.valueOf(catas.getFecha()));
        TextView textViewCodCafe = (TextView) view.findViewById(R.id.textViewCodCafe);
        textViewCodCafe.setText(String.valueOf(catas.getCodCafe()));

        return view;
    }
}
