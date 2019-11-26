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

public class RegistrarCataAdapter extends ArrayAdapter<String> {

    private List<String> atributos;
    private Context context;
    //private TextView numAtributo;
    //private List<Integer> datos;

    public RegistrarCataAdapter(Context context, List<String> atributos) {
        super(context, R.layout.registrar_cata_layout, atributos);
        this.atributos = atributos;
        this.context = context;
        //this.datos = new ArrayList<>(atributos.size());
    }

    @NonNull
    @Override
    public View getView(final int position, @NonNull View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.registrar_cata_layout, parent, false);
        String atributo = this.atributos.get(position);
        TextView textViewAtributo =  view.findViewById(R.id.textViewAtributo);
        textViewAtributo.setText(atributo);
        /*SeekBar seekBarAtributo = (SeekBar) view.findViewById(R.id.seekBarAtributo);
        numAtributo = (TextView) view.findViewById(R.id.numAtributo);
        numAtributo.setText("" + seekBarAtributo.getProgress());
        numAtributo.setFocusable(true);
        datos.add(seekBarAtributo.getProgress());
        seekBarAtributo.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                System.out.println("" + i);
                datos.add(position, i);
                numAtributo.setText(i + "");
                Toast.makeText(context, "" + i, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });*/
        return view;
    }

    /*public List<Integer> getDatos() {
        return datos;
    }

    public TextView getNumAtributo() {
        return numAtributo;
    }*/
}
