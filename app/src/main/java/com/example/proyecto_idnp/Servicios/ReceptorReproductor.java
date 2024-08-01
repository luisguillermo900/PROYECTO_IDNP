package com.example.proyecto_idnp.Servicios;

import static android.content.ContentValues.TAG;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class ReceptorReproductor extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent actualizarUI = new Intent("ACTUALIZAR_UI");
        Intent serviceIntent = new Intent(context, ServicioAudio.class);
        String nombreArchivo = intent.getStringExtra("nombreArchivo");
        boolean estaReproduciendo = intent.getBooleanExtra("estaReproduciendo",false);
        String accion = intent.getAction();
        switch (accion){
            case ("ACCION_REPRODUCIR_PAUSAR"):
                Log.d(TAG, intent.getAction());
                serviceIntent.setAction("ACCION_REPRODUCIR_PAUSAR");
                serviceIntent.putExtra("nombreArchivo", nombreArchivo);
                actualizarUI.putExtra("estaReproduciendo", estaReproduciendo);
                break;
            case("ACCION_REINICIAR"):
                Log.d(TAG, intent.getAction());
                serviceIntent.setAction("ACCION_REINICIAR");
                serviceIntent.putExtra("nombreArchivo", nombreArchivo);
                actualizarUI.putExtra("estaReproduciendo", true);
                break;
            case("ACCION_DETENER"):
                Log.d(TAG, "BroadcastReceiver: " + intent.getAction());
                serviceIntent.setAction("ACCION_DETENER");
                serviceIntent.putExtra("nombreArchivo", nombreArchivo);
                actualizarUI.putExtra("estaReproduciendo", false);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + intent.getAction());
        }
        LocalBroadcastManager.getInstance(context).sendBroadcast(actualizarUI);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(serviceIntent);
        } else {
            context.startService(serviceIntent);
        }
    }
}

