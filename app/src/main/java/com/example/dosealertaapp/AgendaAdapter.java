package com.example.dosealertaapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

public class AgendaAdapter extends ArrayAdapter<ModeloDados> {

    private Context context;
    private List<ModeloDados> listaAgendamento;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onEditarClick(ModeloDados item);

        void onExcluirClick(ModeloDados item);
    }

    public AgendaAdapter(Context context, List<ModeloDados> listaAgendamento, OnItemClickListener listener) {
        super(context, 0, listaAgendamento);
        this.listaAgendamento = listaAgendamento;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ModeloDados agendamento = listaAgendamento.get(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_item_lista, parent, false);
        }

        TextView tvHorario = convertView.findViewById(R.id.tvHorario);
        TextView tvNomeMedicamento = convertView.findViewById(R.id.tvNomeMedicamento);
        ImageButton btnEditar = convertView.findViewById(R.id.btnEditar);
        ImageButton btnExcluir = convertView.findViewById(R.id.btnExcluir);

        tvHorario.setText(agendamento.getHora());
        tvNomeMedicamento.setText(agendamento.getNomeMedicamento());

        btnEditar.setOnClickListener(v -> {
            if (listener != null) listener.onEditarClick(agendamento);
        });

        btnExcluir.setOnClickListener(v -> {
            if (listener != null) listener.onExcluirClick(agendamento);
        });

        btnEditar.setOnClickListener(v -> {
            if (listener != null) listener.onEditarClick(agendamento);
        });

        return convertView;
    }


}