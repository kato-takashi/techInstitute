package com.example.katotakashi.myservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by katotakashi on 15/05/11.
 */
public class MyService extends Service {
    static final String TAG = "LocalService";
    @Override
    public void onCreate() {
        Log.i(TAG, "onCreate");
        Toast.makeText(this, "MyService#onCreate", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand Received start id" + startId + "+ " + intent);
        Toast.makeText(this, "MyService#onStartCommand", Toast.LENGTH_SHORT).show();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy");
        Toast.makeText(this, "MyService#onDestroy", Toast.LENGTH_SHORT).show();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
