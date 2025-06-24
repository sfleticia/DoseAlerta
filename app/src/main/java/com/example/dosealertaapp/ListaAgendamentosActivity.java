package com.example.dosealertaapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ListaAgendamentosActivity extends AppCompatActivity {

    private ListView listView;
    private AgendaAdapter adapter;
    private List<ModeloDados> listaAgendamento;
    private BancoControllerAgendamento bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_agendamentos);

        bd = new BancoControllerAgendamento(this);
        listView = findViewById(R.id.listViewAgendamentos);

        carregarAgendamentos();

        adapter = new AgendaAdapter(this, listaAgendamento, new AgendaAdapter.OnItemClickListener() {
            @Override
            public void onEditarClick(ModeloDados item) {
                Intent intent = new Intent(ListaAgendamentosActivity.this, EditarAgendamentoActivity.class);
                intent.putExtra("idAgendamento", item.getIdAgendamento());
                intent.putExtra("medicamento", item.getNomeMedicamento());
                intent.putExtra("data", item.getData());
                intent.putExtra("hora", item.getHora());
                intent.putExtra("intervalo", item.getIntervalo());
                intent.putExtra("dias", item.getDias());
                startActivity(intent);
            }

            @Override
            public void onExcluirClick(ModeloDados item) {
                String resultado = bd.deletarAgendamento(item.getIdAgendamento());
                Toast.makeText(ListaAgendamentosActivity.this, resultado, Toast.LENGTH_SHORT).show();
                listaAgendamento.clear();
                carregarAgendamentos();
                adapter.notifyDataSetChanged();
            }
        });

        listView.setAdapter(adapter);

        FloatingActionButton fabAdd = findViewById(R.id.fabAdd);
        fabAdd.setOnClickListener(view -> {
            Intent intent = new Intent(ListaAgendamentosActivity.this, Agendamento.class);
            startActivity(intent);
        });
    }

    private void carregarAgendamentos() {
        if (listaAgendamento == null) {
            listaAgendamento = new ArrayList<>();
        } else {
            listaAgendamento.clear();
        }

        Cursor cursor = bd.getTodosAgendamentos();

        if (cursor != null && cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex("idAgendamento");
            int medicamentoIndex = cursor.getColumnIndex("medicamento");
            int dataIndex = cursor.getColumnIndex("data");
            int horaIndex = cursor.getColumnIndex("hora");
            int intervaloIndex = cursor.getColumnIndex("intervalo");
            int diasIndex = cursor.getColumnIndex("dias");

            do {
                ModeloDados item = new ModeloDados();

                if (idIndex >= 0) item.setIdAgendamento(cursor.getInt(idIndex));
                if (medicamentoIndex >= 0) item.setNomeMedicamento(cursor.getString(medicamentoIndex));
                if (dataIndex >= 0) item.setData(cursor.getString(dataIndex));
                if (horaIndex >= 0) item.setHora(cursor.getString(horaIndex));
                if (intervaloIndex >= 0) item.setIntervalo(cursor.getString(intervaloIndex));
                if (diasIndex >= 0) item.setDias(cursor.getInt(diasIndex));

                listaAgendamento.add(item);
            } while (cursor.moveToNext());

            cursor.close();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (listaAgendamento != null) {
            listaAgendamento.clear();
        }
        carregarAgendamentos();
        adapter.notifyDataSetChanged();
    }
}
