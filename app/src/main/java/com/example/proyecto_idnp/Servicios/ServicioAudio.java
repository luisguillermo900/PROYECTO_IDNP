package com.example.proyecto_idnp.Servicios;

import static android.content.ContentValues.TAG;

import android.app.ForegroundServiceStartNotAllowedException;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.content.pm.ServiceInfo;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.ServiceCompat;

import com.example.proyecto_idnp.R;

import java.io.IOException;

public class ServicioAudio extends Service {
    private String CHANNEL_ID = "canal 100";
    private MediaPlayer reproductor;
    private String tituloCuadro = "";
    @Override
    public void onCreate() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence nombre = "Reproducción de audio";
            String descripcion = "Canal para reproducción de audio";
            int importancia = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel canal = new NotificationChannel(CHANNEL_ID, nombre, importancia);
            canal.setDescription(descripcion);
            // Registra el canal con el sistema
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(canal);
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String textoNotificacion = "Reproduciendo audio";
        tituloCuadro = intent.getStringExtra("nombreCuadro");
        String nombreArchivo = intent.getStringExtra("nombreArchivo");
        String control = intent.getStringExtra("control");
        Log.d(TAG,control);
        if(control.equals("reproducir")){
            try {
                Log.d(TAG,"Inicio Reproduciendo audio");
                reproducirAudio(nombreArchivo);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if (control.equals("pausar")) {
            textoNotificacion = "Audio pausado";
            pausarAudio();
        } else if (control.equals("reiniciar")){
            reiniciarAudio();
        } else if (control.equals("parar")) {
            detenerAudio();
        }
        startForeground(textoNotificacion);
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // We don't provide binding, so return null
        return null;
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "service done", Toast.LENGTH_SHORT).show();
    }

    private void startForeground(String texto){
        try {
            Notification notification =
                    new NotificationCompat.Builder(this, CHANNEL_ID)
                            .setContentTitle(tituloCuadro)
                            .setContentText(texto)
                            .setSmallIcon(R.drawable.baseline_audiotrack_24)
                            .build();
            int type = 0;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                type = ServiceInfo.FOREGROUND_SERVICE_TYPE_MEDIA_PLAYBACK;
            }
            ServiceCompat.startForeground(this, 100, notification, type);
        } catch (Exception e) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S &&
                    e instanceof ForegroundServiceStartNotAllowedException
            ) {
                Toast.makeText(this, "No se puede iniciar el servicio", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void reproducirAudio(String archivo) throws IOException {
        if (archivo != null) {
            if (reproductor == null) {
                Log.d(TAG, "Reproduciendo audio");
                AssetFileDescriptor audioAsset = getAssets().openFd(archivo);
                reproductor = new MediaPlayer();
                reproductor.setDataSource(
                        audioAsset.getFileDescriptor(),
                        audioAsset.getStartOffset(),
                        audioAsset.getLength()
                );
                audioAsset.close();
                reproductor.prepare();
                reproductor.setVolume(1f, 1f);
                reproductor.setLooping(false);
                reproductor.start();
                Toast.makeText(this, "Reproduciendo", Toast.LENGTH_SHORT).show();
            } else if (!reproductor.isPlaying()) {
                reproductor.start();
                Toast.makeText(this, "Reproduciendo", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void detenerAudio() {
        if (reproductor != null) {
            reproductor.stop();
            reproductor.release();
            reproductor = null;
        }
        Toast.makeText(this, "Detener", Toast.LENGTH_SHORT).show();
        stopForeground(true);
        stopSelf();
    }
    private void pausarAudio() {
        if (reproductor != null && reproductor.isPlaying()) {
            reproductor.pause();
            Toast.makeText(this, "Pausar", Toast.LENGTH_SHORT).show();
        }
    }
    private void reiniciarAudio() {
        if (reproductor != null) {
            reproductor.seekTo(0);
            reproductor.start();
            Toast.makeText(this, "Reiniciar", Toast.LENGTH_SHORT).show();
        }
    }
}