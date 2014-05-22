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

public class RouteService extends IntentService {
    public RouteService() {
        super("RouteService");
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

        String url = "http://go.openrise.org:5000/api/v1.0/route";
        String jsonStr = HttpConnectionHandler.ServiceCall(url, "GET");
         //       "{\"route\": {\"nodes\": [{\"text\": \"\\u041f\\u0440\\u043e\\u0439\\u0434\\u0438\\u0442\\u0435 200 \\u043c \\u043f\\u043e \\u0443\\u043b\\u0438\\u0446\\u0435 \\u041c\\u0430\\u0440\\u043e\\u0441\\u0435\\u0439\\u043a\\u0430\", \"geo\": [\"55.757363\", \"37.633339\"], \"id\": 0}, {\"text\": \"\\u041f\\u043e\\u0432\\u0435\\u0440\\u043d\\u0438\\u0442\\u0435 \\u043d\\u0430 \\u0411\\u043e\\u043b\\u044c\\u0448\\u043e\\u0439 \\u0421\\u043f\\u0430\\u0441\\u043e\\u0433\\u043b\\u0438\\u043d\\u0438\\u0449\\u0435\\u0432\\u0441\\u043a\\u0438\\u0439 \\u043f\\u0435\\u0440\\u0435\\u0443\\u043b\\u043e\\u043a\", \"geo\": [\"55.757363\", \"37.633339\"], \"id\": 1}, {\"text\": \"\\u041f\\u0440\\u043e\\u0439\\u0434\\u0438\\u0442\\u0435 \\u0434\\u043e \\u041c\\u0430\\u043b\\u043e\\u0433\\u043e \\u0421\\u043f\\u0430\\u0441\\u043e\\u0433\\u043b\\u0438\\u043d\\u0438\\u0449\\u0435\\u0432\\u0441\\u043a\\u043e\\u0433\\u043e \\u043f\\u0435\\u0440\\u0435\\u0443\\u043b\\u043a\\u0430\", \"geo\": [\"55.757363\", \"37.633339\"], \"id\": 2}, {\"text\": \"\\u041f\\u0440\\u043e\\u0439\\u0434\\u0438\\u0442\\u0435 100 \\u043c \\u0434\\u043e \\u0434\\u043e\\u043c\\u0430 \\u21162\", \"geo\": [\"55.757363\", \"37.633339\"], \"id\": 3}], \"address\": \"\\u041c\\u0430\\u043b\\u044b\\u0439 \\u0421\\u043f\\u0430\\u0441\\u043e\\u0433\\u043b\\u0438\\u043d\\u0438\\u0449\\u0435\\u0432\\u0441\\u043a\\u0438\\u0439 \\u043f\\u0435\\u0440\\u0435\\u0443\\u043b\\u043e\\u043a, \\u0434. 2\", \"tip\": \"\\u0422\\u0443\\u0442 \\u043f\\u043e\\u0434\\u0430\\u044e\\u0442 \\u0437\\u0430\\u043c\\u0435\\u0447\\u0430\\u0442\\u0435\\u043b\\u044c\\u043d\\u044b\\u0435 \\u0431\\u043b\\u0438\\u043d\\u0447\\u0438\\u043a\\u0438 \\u0441 \\u0438\\u043a\\u0440\\u043e\\u0439. \\u041e\\u0431\\u044f\\u0437\\u0430\\u0442\\u0435\\u043b\\u044c\\u043d\\u043e \\u043f\\u043e\\u043f\\u0440\\u043e\\u0431\\u0443\\u0439\\u0442\\u0435! :)\", \"id\": 0, \"place_name\": \"\\u0421\\u0442\\u0430\\u0440\\u0431\\u0430\\u043a\\u0441\"}}";//

        String JSON_ROOT = "route";

        String JSON_ID = "id";
        String JSON_PLACE_NAME = "place_name";
        String JSON_ADDRESS = "address";
        String JSON_TIP = "tip";

        String JSON_NODE_ID = "id";
        String JSON_NODE_TEXT = "text";
        String JSON_NODE_GEO = "geo";

        JSONObject route;




        final Uri ROUTE_URI = Uri
                .parse("content://"+ CacheContentProvider.AUTHORITY +"/" + CacheContentProvider.ROUTE_PATH);
        final Uri NODE_URI = Uri
                .parse("content://"+ CacheContentProvider.AUTHORITY +"/" + CacheContentProvider.NODE_PATH);

        final String ROUTE_ID = "id";
        final String ROUTE_PLACE_NAME = "place_name";
        final String ROUTE_ADDRESS = "address";
        final String ROUTE_TIP = "tip";

        final String NODE_ID = "id";
        final String NODE_ROUTE_ID= "route_id";
        final String NODE_TEXT = "text";
        final String NODE_GEO = "geo";



        if (jsonStr != null) {
            try {
                JSONObject jsonObj = new JSONObject(jsonStr);
                route = jsonObj.getJSONObject(JSON_ROOT);

                ContentValues routeCV = new ContentValues();
                routeCV.put(ROUTE_ID, route.getInt(JSON_ID));
                routeCV.put(ROUTE_PLACE_NAME, route.getString(JSON_PLACE_NAME));
                routeCV.put(ROUTE_ADDRESS, route.getString(JSON_ADDRESS));
                routeCV.put(ROUTE_TIP, route.getString(JSON_TIP));

//                getContentResolver().insert(ROUTE_URI, routeCV);

                ContentValues nodeCV = new ContentValues();
                JSONArray nodes = route.getJSONArray("nodes");
                for (int i = 0; i < nodes.length(); i++) {
                    JSONObject node = nodes.getJSONObject(i);

                    nodeCV.put(NODE_ROUTE_ID, route.getString(JSON_ID));    //не ошибка. Берется из route
                    nodeCV.put(NODE_ID, node.getString(JSON_NODE_ID));
                    nodeCV.put(NODE_TEXT, node.getString(JSON_NODE_TEXT));
                    nodeCV.put(NODE_GEO, node.getString(JSON_NODE_GEO));

                    getContentResolver().insert(NODE_URI, nodeCV);
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
