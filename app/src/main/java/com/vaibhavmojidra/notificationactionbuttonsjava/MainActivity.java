package com.vaibhavmojidra.notificationactionbuttonsjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.databinding.DataBindingUtil;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import com.vaibhavmojidra.notificationactionbuttonsjava.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private final String CHANNEL_ID="MESSAGE";
    private final String CHANNEL_NAME="MESSAGE";
    private NotificationManagerCompat manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        binding.showNotification.setOnClickListener(v -> {
            manager=NotificationManagerCompat.from(MainActivity.this);
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
                NotificationChannel channel=new NotificationChannel(CHANNEL_ID,CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
                manager.createNotificationChannel(channel);
            }

            //For Info Action Button
            PendingIntent pendingIntentInfo=PendingIntent.getActivity(MainActivity.this,0,new Intent(MainActivity.this,InfoActivity.class),PendingIntent.FLAG_UPDATE_CURRENT);
            NotificationCompat.Action actionInfo=new NotificationCompat.Action.Builder(0,"Info",pendingIntentInfo).build();

            //For Settings Action Button
            PendingIntent pendingIntentSettings=PendingIntent.getActivity(MainActivity.this,0,new Intent(MainActivity.this,SettingsActivity.class),PendingIntent.FLAG_UPDATE_CURRENT);
            NotificationCompat.Action actionSettings=new NotificationCompat.Action.Builder(0,"Settings",pendingIntentSettings).build();

            Notification notification=new NotificationCompat.Builder(MainActivity.this,CHANNEL_ID)
                    .setSmallIcon(android.R.drawable.ic_notification_overlay)
                    .setContentTitle("Notification Title")
                    .setContentText("Notification Text")
                    .setAutoCancel(true)
                    .addAction(actionInfo) //Setting Info Action
                    .addAction(actionSettings) //Setting Settings Action
                    .build();
            manager.notify(2,notification);
        });
    }
}