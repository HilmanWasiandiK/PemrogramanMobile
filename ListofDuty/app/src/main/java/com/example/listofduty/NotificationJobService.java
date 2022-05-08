package com.example.listofduty;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;

import androidx.core.app.NotificationCompat;

//Periodic JobService untuk menampilkan notifikasi harian
public class NotificationJobService extends JobService {
    NotificationManager mNotifyManager;

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        Log.i("DailyNotificationService", "onStartJob2");

        createNotificationChannel();

        PendingIntent contentPendingIntent = PendingIntent.getActivity
                (this, 0, new Intent(this, SplashScreen.class),
                        PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder
                (this, "primary_notification_Channel")
                .setContentTitle("Hi, ayo periksa tugas yang kamu miliki!")
                .setContentText("Ketuk notifikasi untuk membuka aplikasi!")
                .setContentIntent(contentPendingIntent)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setAutoCancel(true);

        mNotifyManager.notify(0, builder.build());
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        Log.i("DailyNotificationService", "onStopJob2");
        return false;
    }

    public void createNotificationChannel() {
        mNotifyManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        // Notification channels are only available in OREO and higher.
        // So, add a check on SDK version.
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            // Create the NotificationChannel with all the parameters.
            NotificationChannel notificationChannel = new NotificationChannel
                    ("primary_notification_Channel",
                            "Job Service Notification",
                            NotificationManager.IMPORTANCE_HIGH);

            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription("Ini Deskripsi");

            mNotifyManager.createNotificationChannel(notificationChannel);
        }
    }
}
