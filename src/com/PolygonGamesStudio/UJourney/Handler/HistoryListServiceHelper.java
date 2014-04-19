package com.PolygonGamesStudio.UJourney.Handler;

import android.content.Context;
import android.os.Bundle;

public class HistoryListServiceHelper implements ServiceHelper {

    private final Context context;

    public HistoryListServiceHelper(Context context) {
        this.context = context;
    }

    public static class Methods {
        public static final int GET_HISTORY_LIST = 1;
        public static final int REFRESH_HISTORY_LIST = 2;
    }

    @Override
    public boolean RunTask(int methodId, Bundle extras) {
        switch (methodId) {
            case Methods.GET_HISTORY_LIST:
                return getHistoryList();
            case Methods.REFRESH_HISTORY_LIST:
                return refreshHistoryList();
        }
        return false;
    }

    private boolean refreshHistoryList() {
        return false;
    }

    private boolean getHistoryList() {
        return false;
    }

}
