package com.example.dosealertaapp;

import androidx.appcompat.app.AppCompatActivity;
import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.util.Calendar;

public class Agendamento extends AppCompatActivity implements View.OnClickListener {

    private static final int REQUEST_CODE_NOTIFICATIONS = 1001;

    EditText etNomeMedicamento, etNumeroDias;
    DatePicker dpAgeData;
    TimePicker timePicker;
    Spinner spAgIntervalo;
    Button btAgeAgendar;

    BancoControllerAgendamento bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendamento);

        etNomeMedicamento = findViewById(R.id.etNomeMedicamento);
        etNumeroDias = findViewById(R.id.etNumeroDias);
        dpAgeData = findViewById(R.id.dpAgeData);
        timePicker = findViewById(R.id.timePicker);
        spAgIntervalo = findViewById(R.id.spAgIntervalo);
        btAgeAgendar = findViewById(R.id.btAgeAgendar);


        timePicker.setIs24HourView(true);

        btAgeAgendar.setOnClickListener(this);


        String[] intervalos = {"4h", "6h", "8h", "12h", "24h"};
        ArrayAdapter<String> adapterIntervalo = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, intervalos);
        adapterIntervalo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spAgIntervalo.setAdapter(adapterIntervalo);

        bd = new BancoControllerAgendamento(this);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.POST_NOTIFICATIONS}, REQUEST_CODE_NOTIFICATIONS);
            }
        }
    }

    @Override
    public void onClick(View v) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.POST_NOTIFICATIONS}, REQUEST_CODE_NOTIFICATIONS);
                return;
            }
        }
        agendarMedicamento();
    }

    private void agendarMedicamento() {
        int day = dpAgeData.getDayOfMonth();
        int month = dpAgeData.getMonth() + 1;
        int year = dpAgeData.getYear();

        String data = String.format("%02d/%02d/%04d", day, month, year);

        int horaAgendada = timePicker.getHour();
        int minutoAgendado = timePicker.getMinute();

        String hora = String.format("%02d:%02d", horaAgendada, minutoAgendado);

        String intervalo = spAgIntervalo.getSelectedItem().toString();
        String nomeMedicamento = etNomeMedicamento.getText().toString().trim();
        String numeroDiasStr = etNumeroDias.getText().toString().trim();

        if (nomeMedicamento.isEmpty()) {
            Toast.makeText(this, "Digite o nome do medicamento", Toast.LENGTH_SHORT).show();
            return;
        }

        if (numeroDiasStr.isEmpty()) {
            Toast.makeText(this, "Digite o número de dias", Toast.LENGTH_SHORT).show();
            return;
        }

        int numeroDias;
        try {
            numeroDias = Integer.parseInt(numeroDiasStr);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Número de dias inválido", Toast.LENGTH_SHORT).show();
            return;
        }

        if (consulta(data, hora)) {
            String resultado = bd.insereAgendamento(data, hora, "", nomeMedicamento, intervalo, numeroDias);
            Toast.makeText(this, resultado, Toast.LENGTH_LONG).show();

            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month - 1);
            calendar.set(Calendar.DAY_OF_MONTH, day);
            calendar.set(Calendar.HOUR_OF_DAY, horaAgendada);
            calendar.set(Calendar.MINUTE, minutoAgendado);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);

            if (calendar.getTimeInMillis() <= System.currentTimeMillis()) {
                calendar.add(Calendar.DAY_OF_MONTH, 1);
            }

            Intent intentAlarme = new Intent(this, AlarmeReceiver.class);
            intentAlarme.putExtra("remedio", nomeMedicamento);

            PendingIntent pendingIntent = PendingIntent.getBroadcast(
                    this,
                    (int) System.currentTimeMillis(),
                    intentAlarme,
                    PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
            );

            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            if (alarmManager != null) {
                alarmManager.setExactAndAllowWhileIdle(
                        AlarmManager.RTC_WAKEUP,
                        calendar.getTimeInMillis(),
                        pendingIntent
                );
            }


            Intent intent = new Intent(this, ListaAgendamentosActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private boolean consulta(String data, String hora) {
        // Aqui pode implementar a verificação se já existe o agendamento
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_NOTIFICATIONS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                agendarMedicamento();
            } else {
                Toast.makeText(this, "Permissão para notificações negada.", Toast.LENGTH_LONG).show();
            }
        }
    }
}
