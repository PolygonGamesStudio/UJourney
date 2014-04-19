package com.PolygonGamesStudio.UJourney.Service;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import com.PolygonGamesStudio.UJourney.Handler.HttpConnectionHandler;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
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
        String url = "http://127.0.0.1:5000/api/v1.0/category";
        String jsonStr = HttpConnectionHandler.ServiceCall(url, "GET");

        JSONArray category;
        ArrayList<HashMap<String, String>> categoryList = new ArrayList<HashMap<String, String>>();

        String JSON_ROOT = "Category";

        String JSON_TITLE = "title";
        String JSON_DESCRIPTION = "description";
        String JSON_PICTURE = "picture";

        Log.d(LOG_TAG, "Hello");

        if (jsonStr != null) {
            try {
                JSONObject jsonObj = new JSONObject(jsonStr);
                category = jsonObj.getJSONArray(JSON_ROOT);

                for (int i = 0; i < category.length(); i++) {
                    JSONObject c = category.getJSONObject(i);

                    HashMap<String, String> contact = new HashMap<String, String>();

//                    TODO:тут вроде нехуя не работает, но логика верна

                    contact.put(JSON_TITLE, c.getString(JSON_TITLE));
                    contact.put(JSON_DESCRIPTION, c.getString(JSON_DESCRIPTION));
                    contact.put(JSON_PICTURE, c.getString(JSON_PICTURE));

                    categoryList.add(contact);
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

//        TODO: тут пишем в базу после
//        А после CL сам подтянет и отрендерит во вьюхе


    }
}
