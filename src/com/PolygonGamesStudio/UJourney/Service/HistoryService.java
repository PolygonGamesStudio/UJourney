package com.PolygonGamesStudio.UJourney.Service;

import android.app.IntentService;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.IBinder;
import com.PolygonGamesStudio.UJourney.ContentProvider.CacheContentProvider;
import com.PolygonGamesStudio.UJourney.Handler.HttpConnectionHandler;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HistoryService extends IntentService {


    public HistoryService() {
        super("HistoryService");
    }

    public void onCreate() {
        super.onCreate();
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        // TODO: передавать URL ! Парсер json

        String url = "http://192.168.1.15:5000/api/v1.0/history";
        String jsonStr = HttpConnectionHandler.ServiceCall(url, "GET");

        String JSON_ROOT = "history";

        String JSON_ID = "id";
        String JSON_TITLE = "title";
        String JSON_VISIT = "visit";
        String JSON_PICTURE = "picture";

        JSONArray category;


        ContentValues cv = new ContentValues();

        final Uri CONTACT_URI = Uri
                .parse("content://"+ CacheContentProvider.AUTHORITY +"/" + CacheContentProvider.HISTORY_PATH);

        final String CONTACT_ID = "id";
        final String CONTACT_NAME = "title";
        final String CONTACT_VISIT = "visit";
        final String CONTACT_PICTURE = "picture";

        if (jsonStr != null) {
            try {
                JSONObject jsonObj = new JSONObject(jsonStr);
                category = jsonObj.getJSONArray(JSON_ROOT);

                for (int i = 0; i < category.length(); i++) {
                    JSONObject c = category.getJSONObject(i);


                    cv.put(CONTACT_ID, c.getString(JSON_ID));
                    cv.put(CONTACT_NAME, c.getString(JSON_TITLE));
                    cv.put(CONTACT_VISIT, c.getString(JSON_VISIT));
                    cv.put(CONTACT_PICTURE, c.getString(JSON_PICTURE));

                    getContentResolver().insert(CONTACT_URI, cv);
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
