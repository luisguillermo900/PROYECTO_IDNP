package com.example.proyecto_idnp.Servicios;

import static android.content.ContentValues.TAG;

import android.app.ForegroundServiceStartNotAllowedException;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.pm.ServiceInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.OptIn;
import androidx.core.app.NotificationCompat;

import androidx.core.app.ServiceCompat;
import androidx.core.graphics.drawable.IconCompat;
import androidx.media3.common.MediaItem;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.datasource.AssetDataSource;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.DataSpec;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaStyleNotificationHelper;

import com.example.proyecto_idnp.Actividades.HomeActivity;
import com.example.proyecto_idnp.R;

public class ServicioAudio extends Service {
    private MediaSession mediaSession = null;
    private String CHANNEL_ID = "canal 100";
    private MediaItem mediaItem = null;
    private String archivoActual = "";
    private NotificationCompat.Action accionReproducirPausar;
    private NotificationCompat.Action accionReiniciar;
    private NotificationCompat.Action accionDetener;
    private NotificationManager notificationManager;
    private boolean isPlaying = true;
    private Notification notification;

    private ExoPlayer reproductor;
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate() EJECUTADO");
        notificationManager = (NotificationManager) this.getSystemService(NOTIFICATION_SERVICE);
        reproductor = new ExoPlayer.Builder(this).build();
        mediaSession = new MediaSession.Builder(this, reproductor).build();
        iniciarCanal();
    }

    @OptIn(markerClass = UnstableApi.class)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String accion = intent.getAction();
        String nombreArchivo = intent.getStringExtra("nombreArchivo");

        if (mediaItem == null || !archivoActual.equals(nombreArchivo)){
            archivoActual = nombreArchivo;
            AssetDataSource assetDataSource = new AssetDataSource(this);
            DataSpec dataSpec = new DataSpec(Uri.parse("asset:///" + archivoActual));
            try {
                assetDataSource.open(dataSpec);
            } catch (AssetDataSource.AssetDataSourceException e) {
                throw new RuntimeException(e);
            }
            DataSource.Factory factory = () -> assetDataSource;
            mediaItem = new MediaItem.Builder()
                    .setUri(dataSpec.uri)
                    .build();
            Log.d(TAG, "Nombre de archivo: " + mediaItem.toString());
            reproductor.setMediaItem(mediaItem);
            reproductor.prepare();
        }
        switch (accion){
            case ("ACCION_REPRODUCIR_PAUSAR"):
                Log.d(TAG, nombreArchivo);
                reproducirPausarAudio();
                break;
            case("ACCION_REINICIAR"):
                reiniciarAudio();
                //notificationManager.notify(0, notification);
                break;
            case("ACCION_DETENER"):
                detenerAudio();
                //notificationManager.notify(0, notification);
                break;
        }
        crearAcciones();
        iniciarPrimerPlano();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        mediaSession.getPlayer().release();
        mediaSession.release();
        mediaSession = null;
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void iniciarCanal(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence nombre = "Reproducción de audio";
            String descripcion = "Canal para reproducción de audio";
            int importancia = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel canal = new NotificationChannel(CHANNEL_ID, nombre, importancia);
            canal.setDescription(descripcion);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(canal);
        }
    }

    @OptIn(markerClass = UnstableApi.class)
    private void iniciarPrimerPlano(){
        Intent notificationIntent = new Intent(this, HomeActivity.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        notificationIntent.putExtra("openFragment", "TuFragmento");

        PendingIntent pendingIntent = PendingIntent.getActivity(
                this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT
        );
        Bitmap albumArtBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pintura_imagen);
        try {
            notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                    .setSmallIcon(R.drawable.baseline_audiotrack_24)
                    .setContentIntent(pendingIntent)
                    .addAction(accionReproducirPausar)
                    .addAction(accionReiniciar)
                    .addAction(accionDetener)
                    .setStyle(new MediaStyleNotificationHelper.MediaStyle(mediaSession)
                            .setShowActionsInCompactView(0,1,2))
                    .setContentTitle("Wonderful music")
                    .setContentText("My Awesome Band")
                    .setLargeIcon(albumArtBitmap)
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
    private void crearAcciones() {
        Log.d(TAG, "ServicioAudio crearAcciones(): " + isPlaying);
        Intent intentReproPausa = new Intent(this, ReceptorReproductor.class).setAction("ACCION_REPRODUCIR_PAUSAR");
        intentReproPausa.putExtra("nombreArchivo", archivoActual);
        intentReproPausa.putExtra("estaReproduciendo", !isPlaying);
        PendingIntent pendingIntentReproPausa = PendingIntent.getBroadcast(this, 0, intentReproPausa, PendingIntent.FLAG_UPDATE_CURRENT);
        accionReproducirPausar = new NotificationCompat.Action.Builder(
                IconCompat.createWithResource(this,
                        isPlaying ? R.drawable.baseline_pause_24 : R.drawable.baseline_play_arrow_24
                ),
                "ReproducirPausar",
                pendingIntentReproPausa
        ).build();

        Intent intentReiniciar = new Intent(this, ReceptorReproductor.class).setAction("ACCION_REINICIAR");
        intentReiniciar.putExtra("nombreArchivo", archivoActual);
        PendingIntent reiniciarPendingIntent = PendingIntent.getBroadcast(this, 1, intentReiniciar, PendingIntent.FLAG_UPDATE_CURRENT);
        accionReiniciar = new NotificationCompat.Action.Builder(
                IconCompat.createWithResource(this, R.drawable.baseline_skip_previous_24),
                "Reiniciar",
                reiniciarPendingIntent
        ).build();

        Intent intentDetener = new Intent(this, ReceptorReproductor.class).setAction("ACCION_DETENER");
        intentDetener.putExtra("nombreArchivo", archivoActual);
        PendingIntent detenerPendingIntent = PendingIntent.getBroadcast(this, 2, intentDetener, PendingIntent.FLAG_UPDATE_CURRENT);
        accionDetener = new NotificationCompat.Action.Builder(
                IconCompat.createWithResource(this, R.drawable.baseline_stop_24),
                "Detener",
                detenerPendingIntent
        ).build();
    }

    @OptIn(markerClass = UnstableApi.class)
    private void reproducirPausarAudio() {
        if (reproductor != null){
            if (reproductor.isPlaying()){
                isPlaying = false;
                reproductor.pause();
                Toast.makeText(this, "Pausar", Toast.LENGTH_SHORT).show();
            } else {
                isPlaying = true;
                reproductor.play();
                Toast.makeText(this, "Reproduciendo", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void detenerAudio() {
        isPlaying = false;
        if (reproductor != null) {
            reproductor.stop();
            reproductor.release();
            reproductor = null;
        }
        Toast.makeText(this, "Detener", Toast.LENGTH_SHORT).show();
        stopForeground(true);
        stopSelf();
    }

    private void reiniciarAudio() {
        if (reproductor != null) {
            isPlaying = true;
            reproductor.seekTo(0);
            reproductor.play();
            Toast.makeText(this, "Reiniciar", Toast.LENGTH_SHORT).show();
        }
    }
}