package com.example.alaramclock;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Cancel_alram_activity extends AppCompatActivity {
TextView txt_time ;
Button btnDismiss;
    PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_alram);

//        to wake the screen up when it is locked
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON|
                WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD|
                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED|
                WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
        txt_time=findViewById(R.id.txt_time);
        btnDismiss=findViewById(R.id.btnDismiss);
        Calendar c = Calendar.getInstance();

        SimpleDateFormat df = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");
        String formattedDate = df.format(c.getTime());
        // formattedDate have current date/time

        txt_time.setText(formattedDate);
        btnDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Cancel_alram_activity.this, "Dismiss", Toast.LENGTH_SHORT).show();
                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                Intent intent = new Intent(getApplicationContext(), AlaramReceiver.class);
                pendingIntent = PendingIntent.getBroadcast(Cancel_alram_activity.this, 1, intent, PendingIntent.FLAG_MUTABLE);
                alarmManager.cancel(pendingIntent);
            }
        });

    }




}