package com.example.dosealertaapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditarAgendamentoActivity extends AppCompatActivity {

    private EditText etMedicamento, etHora;
    private Button btnSalvar;

    private BancoControllerAgendamento bd;

    private int idAgendamento;
    private String dataOriginal;
    private String intervaloOriginal;
    private int diasOriginal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_agendamento);

        etMedicamento = findViewById(R.id.etMedicamentoEditar);
        etHora = findViewById(R.id.etHoraEditar);
        btnSalvar = findViewById(R.id.btnSalvarEdicao);

        bd = new BancoControllerAgendamento(this);

        // Recupera os dados passados pela Intent
        idAgendamento = getIntent().getIntExtra("idAgendamento", -1);
        String nome = getIntent().getStringExtra("medicamento");
        String hora = getIntent().getStringExtra("hora");
        dataOriginal = getIntent().getStringExtra("data");
        intervaloOriginal = getIntent().getStringExtra("intervalo");
        diasOriginal = getIntent().getIntExtra("dias", 0);


        etMedicamento.setText(nome);
        etHora.setText(hora);

        btnSalvar.setOnClickListener(v -> {
            String novoMedicamento = etMedicamento.getText().toString().trim();
            String novaHora = etHora.getText().toString().trim();

            if (novoMedicamento.isEmpty() || novaHora.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                return;
            }

            String resultado = bd.atualizarAgendamento(
                    idAgendamento,
                    novoMedicamento,
                    dataOriginal,
                    novaHora,
                    intervaloOriginal,
                    diasOriginal
            );

            Toast.makeText(this, resultado, Toast.LENGTH_SHORT).show();

            if (resultado.contains("sucesso")) {
                finish(); // Fecha a tela de edição
            }
        });
    }
}
