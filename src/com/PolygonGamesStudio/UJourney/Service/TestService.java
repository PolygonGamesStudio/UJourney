package com.PolygonGamesStudio.UJourney.Service;

import android.app.IntentService;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.IBinder;
import android.util.Log;
import com.PolygonGamesStudio.UJourney.Handler.HttpConnectionHandler;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class TestService extends IntentService {

    final String LOG_TAG = "myLogs";

    public TestService() {
        super("TestService");
    }

    public void onCreate() {
        super.onCreate();
        Log.d(LOG_TAG, "onCreate");
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(LOG_TAG, "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    public void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "onDestroy");
    }

    public IBinder onBind(Intent intent) {
        Log.d(LOG_TAG, "onBind");
        return null;
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        // TODO: передавать URL ! Парсер json

        String url = "http://192.168.1.15:5000/api/v1.0/category";
        String jsonStr = HttpConnectionHandler.ServiceCall(url, "GET");

        String JSON_ROOT = "category";

        String JSON_TITLE = "title";
        String JSON_DESCRIPTION = "description";
        String JSON_PICTURE = "picture";

        JSONArray category;


        ContentValues cv = new ContentValues();

        final Uri CONTACT_URI = Uri
                .parse("content://com.PolygonGamesStudio.UJourney.providers.AdressBook/contacts");

        final String CONTACT_NAME = "name";
        final String CONTACT_EMAIL = "email";

        if (jsonStr != null) {
            try {
                JSONObject jsonObj = new JSONObject(jsonStr);
                category = jsonObj.getJSONArray(JSON_ROOT);

                for (int i = 0; i < category.length(); i++) {
                    JSONObject c = category.getJSONObject(i);

                    cv.put(CONTACT_NAME, c.getString(JSON_TITLE));
                    cv.put(CONTACT_EMAIL, c.getString(JSON_DESCRIPTION));
//                    TODO: Дропать и записывать, сейчс запись в конец
                    getContentResolver().insert(CONTACT_URI, cv);
//                    c.getString(JSON_PICTURE)
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
