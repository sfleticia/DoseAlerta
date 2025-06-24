package com.example.dosealertaapp;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;

public class AlarmeReceiver extends BroadcastReceiver {

    private static MediaPlayer mediaPlayer;

    @Override
    public void onReceive(Context context, Intent intent) {
        String nomeRemedio = intent.getStringExtra("remedio");


        if (mediaPlayer == null) {
            Uri somUri = Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.notificacao);
            mediaPlayer = MediaPlayer.create(context, somUri);
            mediaPlayer.setLooping(true); // toca em loop
        }
        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.start();
        }


        String canalId = "canal_remedio";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel canal = new NotificationChannel(
                    canalId,
                    "Lembrete de Remédio",
                    NotificationManager.IMPORTANCE_HIGH
            );
            canal.setSound(null, null); // sem som na notificação
            NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            manager.createNotificationChannel(canal);
        }


        Intent intentTela = new Intent(context, AlarmeActivity.class);
        intentTela.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intentTela.putExtra("remedio", nomeRemedio);
        context.startActivity(intentTela);
    }

    public static void pararAlarme() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
