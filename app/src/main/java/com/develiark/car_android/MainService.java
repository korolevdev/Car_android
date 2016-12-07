package com.develiark.car_android;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MainService extends Service {

    public void onCreate() {
        super.onCreate();
        MainActivity.mainSocket = new MainSocket();
        Thread t = new Thread(MainActivity.mainSocket);
        t.start();
    }

    public IBinder onBind(Intent arg0) {
        return null;
    }
}