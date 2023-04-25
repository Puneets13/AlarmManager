package com.example.alaramclock;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Vibrator;
import android.provider.Settings;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AlaramReceiver extends BroadcastReceiver {
    MediaPlayer mp;
    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void onReceive(Context context, Intent intent) {

        // we will use vibrator first
        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(4000);

        Toast.makeText(context, "Alarm! Wake up! Wake up!", Toast.LENGTH_LONG).show();
        Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if (alarmUri == null) {
            alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }
        // setting default ringtone
        Ringtone ringtone = RingtoneManager.getRingtone(context, alarmUri);
        // play ringtone
        ringtone.play();



//Creating Intent and pending intent for the notifications of Alarm;
        Intent wakeIntent = new Intent(context, Cancel_alram_activity.class);
        wakeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pi = PendingIntent.getActivity(context,0,wakeIntent,PendingIntent.FLAG_MUTABLE);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");
        String formattedDate = df.format(calendar.getTime());
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,"Alarm")
                .setSmallIcon(R.drawable.ic_time)
                .setContentTitle("Alarm..")
                .setContentTitle(formattedDate)
                .setAutoCancel(true)
                .addAction(R.drawable.ic_time,"DISMISS",pi)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pi);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(100,builder.build());

//
//        String whichAction = intent.getAction();
//        switch (whichAction)
//        {
//
//            case "DISMISS":
//                ringtone.stop();
//                return;
//
//        }

    }
}
