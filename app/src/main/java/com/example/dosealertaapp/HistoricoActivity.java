package com.example.dosealertaapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HistoricoActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private HistoricoAdapter historicoAdapter;
    private List<Historico> historicoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        historicoList = new ArrayList<>();


        historicoList.add(new Historico("Dipirona", "500mg", "08/06/2025 14:00"));
        historicoList.add(new Historico("Paracetamol", "750mg", "07/06/2025 20:00"));
        historicoList.add(new Historico("Amoxicilina", "250mg", "06/06/2025 09:00"));

        historicoAdapter = new HistoricoAdapter(historicoList);
        recyclerView.setAdapter(historicoAdapter);
    }
}
