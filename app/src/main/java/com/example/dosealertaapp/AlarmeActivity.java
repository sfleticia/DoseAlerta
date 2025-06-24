package com.example.dosealertaapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.dosealertaapp.HomeActivity;

import androidx.appcompat.app.AppCompatActivity;



import androidx.appcompat.app.AppCompatActivity;

public class AlarmeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarme);

        TextView tvAlarme = findViewById(R.id.tvAlarme);
        String nomeRemedio = getIntent().getStringExtra("remedio");
        tvAlarme.setText("Hora do Remédio!\nTome: " + nomeRemedio);

        Button btnParar = findViewById(R.id.btnParar);
        btnParar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlarmeReceiver.pararAlarme();


                Intent intent = new Intent(AlarmeActivity.this, HomeActivity.class);
                startActivity(intent);


                finish();

            }
        });
    }
}

