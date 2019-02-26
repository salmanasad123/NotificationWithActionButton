package com.example.teamas.notificationwithactionbutton;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.support.v4.app.NotificationManagerCompat;

public class NotificationApp extends Application {

    public static final String CHANNEL_ID_1 = "channel1";

    // first we create notification channel before our main activity is called
    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannel();
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel notificationChannel = new NotificationChannel(
                    CHANNEL_ID_1,
                    "Channel 1",
                    NotificationManager.IMPORTANCE_HIGH);

            notificationChannel.enableLights(true);
            notificationChannel.enableVibration(false);
            notificationChannel.setLightColor(R.color.colorPrimaryDark);
            notificationChannel.setDescription("This is Channel 1");

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(notificationChannel);
            }
        }
    }
}
