package com.example.dosealertaapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HistoricoAdapter extends RecyclerView.Adapter<HistoricoAdapter.HistoricoViewHolder> {

    private List<Historico> historicoList;

    public HistoricoAdapter(List<Historico> historicoList) {
        this.historicoList = historicoList;
    }

    @Override
    public HistoricoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_historico, parent, false);
        return new HistoricoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HistoricoViewHolder holder, int position) {
        Historico historico = historicoList.get(position);
        holder.nomeTextView.setText(historico.getNome());
        holder.doseTextView.setText(historico.getDose());
        holder.dataHoraTextView.setText(historico.getDataHora());
        holder.imageView.setImageResource(R.drawable.image);
    }

    @Override
    public int getItemCount() {
        return historicoList.size();
    }

    public static class HistoricoViewHolder extends RecyclerView.ViewHolder {
        TextView nomeTextView, doseTextView, dataHoraTextView;
        ImageView imageView;

        public HistoricoViewHolder(View itemView) {
            super(itemView);
            nomeTextView = itemView.findViewById(R.id.tvNome);
            doseTextView = itemView.findViewById(R.id.tvDose);
            dataHoraTextView = itemView.findViewById(R.id.tvDataHora);
            imageView = itemView.findViewById(R.id.ivMedicamento);
        }
    }
}
