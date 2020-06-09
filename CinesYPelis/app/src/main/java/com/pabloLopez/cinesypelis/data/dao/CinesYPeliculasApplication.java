package com.pabloLopez.cinesypelis.data.dao;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import com.pabloLopez.cinesypelis.R;

//IMPORTANTE añadir al manifieste esta clase  android:name=".data.dao.CinesYPeliculasApplication"
public class CinesYPeliculasApplication extends Application {
    public static final String CHANNEL_ID = "1";

    @Override
    public void onCreate() {
        super.onCreate();
        CinesYPelisDatabase.create(this);//Creamos la BD
        createNotificationChannel();
    }

    //Creacon de canal de notificaciones
    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.nombreCanalNotificaciones);
            String description = getString(R.string.descripcionCanalNotificaciones);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
