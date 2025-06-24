package com.example.dosealertaapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

public class HomeActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private Toolbar toolbar;

    private LinearLayout btnNotificacoes, btnAgendar, btnMedicamentos, btnHistorico, btnSair;
    private TextView tvUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        drawerLayout = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        btnNotificacoes = findViewById(R.id.btn_notificacoes);
        btnAgendar = findViewById(R.id.btn_agendar);
        btnMedicamentos = findViewById(R.id.btn_medicamentos);
        btnHistorico = findViewById(R.id.btn_historico);
        btnSair = findViewById(R.id.btn_sair);
        tvUserName = findViewById(R.id.tvUserName);

        BancoController crud = new BancoController(this);
        SharedPreferences preferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
        String emailLogado = preferences.getString("email_usuario_logado", null);

        if (emailLogado != null) {
            String nomeUsuario = crud.getNomeUsuario(emailLogado);
            tvUserName.setText(nomeUsuario);
        }

        btnNotificacoes.setOnClickListener(v -> {
            Toast.makeText(this, "Notificações clicado", Toast.LENGTH_SHORT).show();
            drawerLayout.closeDrawers();
        });

        btnAgendar.setOnClickListener(v -> {
            startActivity(new Intent(this, Agendamento.class));
            drawerLayout.closeDrawers();
        });

        btnMedicamentos.setOnClickListener(v -> {
            startActivity(new Intent(this, ListaAgendamentosActivity.class));
            drawerLayout.closeDrawers();
        });

        btnHistorico.setOnClickListener(v -> {
            startActivity(new Intent(this, HistoricoActivity.class));
            drawerLayout.closeDrawers();
        });

        btnSair.setOnClickListener(v -> {
            finish();
        });


        Button btnIrAgendar = findViewById(R.id.btn_ir_agendar);
        btnIrAgendar.setOnClickListener(v -> {
            startActivity(new Intent(this, Agendamento.class));
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}



