package com.example.teamas.notificationwithactionbutton;

import android.app.Activity;
import android.app.RemoteInput;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {


    private static final String TAG = "MTAG";
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        textView = findViewById(R.id.tv_reply);
        Intent intent = this.getIntent();
        CharSequence data = getMessageText(intent);
        Log.d(TAG, "onCreate: " + data);
        if (data != null) {
            textView.setText(data.toString());
        }
    }

    private CharSequence getMessageText(Intent intent) {
        Bundle remoteInput = RemoteInput.getResultsFromIntent(intent);
        if (remoteInput != null) {
            return remoteInput.getCharSequence("ReplyKey");
        }
        return null;
    }
}
