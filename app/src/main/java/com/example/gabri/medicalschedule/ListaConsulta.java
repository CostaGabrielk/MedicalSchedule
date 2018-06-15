package com.example.gabri.medicalschedule;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.List;

/**
 * Created by Gabri on 06/06/2018.
 */

public class ListaConsulta extends ArrayAdapter<Consulta>{

    private Activity context;
    private List<Consulta> listaConsulta;

    public ListaConsulta(Activity context, List<Consulta> listaConsulta){
        super(context, R.layout.list_layout, listaConsulta);
        this.context = context;
        this.listaConsulta = listaConsulta;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_layout, null, true);

        TextView textViewNome = (TextView) listViewItem.findViewById(R.id.txtvwNome);
        TextView textViewFone = (TextView) listViewItem.findViewById(R.id.txtvwFone);
        TextView textViewData= (TextView) listViewItem.findViewById(R.id.txtvwData);
        TextView textViewMedico = (TextView) listViewItem.findViewById(R.id.txtvwMedico);

        Consulta consulta = listaConsulta.get(position);

        textViewNome.setText(consulta.getNomePacinte());
        textViewFone.setText(consulta.getFonePaciente());
        textViewData.setText(consulta.getDataConsulta());
        textViewMedico.setText(consulta.getMedicoSelecionado());

        return listViewItem;
    }
}
