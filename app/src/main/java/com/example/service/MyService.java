package com.example.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.widget.Toast;

public class MyService extends Service {

    MediaPlayer mediaPlayer;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {

       // mediaPlayer = MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI);
      //  mediaPlayer.setLooping(true);

        mediaPlayer = MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();

        super.onCreate();

        Toast.makeText(this,"Service is created", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Toast.makeText(this,"Service is started", Toast.LENGTH_SHORT).show();
        //stopSelf();//optional to stop the music without clicking stop button
        //return super.onStartCommand(intent, flags, startId);
        return START_STICKY;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mediaPlayer!=null && mediaPlayer.isPlaying()){
            mediaPlayer.stop();
            mediaPlayer.reset();
            mediaPlayer.release();
        }
        Toast.makeText(this,"Service is stopped", Toast.LENGTH_SHORT).show();
    }
}
