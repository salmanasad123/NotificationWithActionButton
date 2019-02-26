package com.example.teamas.notificationwithactionbutton;

import android.app.ActionBar;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.RemoteInput;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Button triggerNotification;
    private NotificationManagerCompat notificationManagerCompat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.my_toolbar);
        toolbar.setTitle("Notification Application");
        setSupportActionBar(toolbar);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        notificationManagerCompat = NotificationManagerCompat.from(this);
        triggerNotification = findViewById(R.id.btn_trigger_notification);

        final android.support.v4.app.RemoteInput remoteInput = new android.support.v4.app.RemoteInput.Builder("ReplyKey")
                .setLabel("Reply")
                .build();


        Intent intent = new Intent(this, Main2Activity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        final PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);


        final NotificationCompat.Action action = new NotificationCompat.Action.Builder(R.drawable.ic_launcher_foreground,
                "Reply", pendingIntent)
                .addRemoteInput(remoteInput)
                .build();

        triggerNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Notification builder = new NotificationCompat.Builder(MainActivity.this, NotificationApp.CHANNEL_ID_1)
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setAutoCancel(true)
                        .setContentTitle("Title")
                        .setContentText("Notification Text")
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        .setContentIntent(pendingIntent)
                        .addAction(action)
                        .build();

                notificationManagerCompat.notify(1, builder);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_one:
                Toast.makeText(this, "Item one clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_two:
                Toast.makeText(this, "Item two clicked", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
