package com.PolygonGamesStudio.UJourney;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.PolygonGamesStudio.UJourney.Handler.HttpConnectionHandler;

public class ActivityForService extends Activity{
    final String LOG_TAG = "myLogs";

    Intent intent;
    ServiceConnection sConn;
    boolean bound = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_service_activity);

        intent = new Intent("com.PolygonGamesStudio.UJourney.MyService");
        sConn = new ServiceConnection() {
            public void onServiceConnected(ComponentName name, IBinder binder) {
                Log.d(LOG_TAG, "MainActivity onServiceConnected");
                bound = true;
            }

            public void onServiceDisconnected(ComponentName name) {
                Log.d(LOG_TAG, "MainActivity onServiceDisconnected");
                bound = false;
            }
        };


    }


    public void onClickStart(View v) {
        startService(intent);
    }

    public void onClickStop(View v) {
        stopService(intent);
    }

    public void onClickBind(View v) {
        bindService(intent, sConn, BIND_AUTO_CREATE);
        HttpConnectionHandler handler = new HttpConnectionHandler();
        String result = handler.ServiceCall("http://192.168.43.61:5000/history/23", "GET");
        Toast newToast = Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT);
    }

    public void onClickUnBind(View v) {
        if (!bound) return;
        unbindService(sConn);
        bound = false;
    }

    protected void onDestroy() {
        super.onDestroy();
        onClickUnBind(null);
    }

}
