package com.PolygonGamesStudio.UJourney.AsyncTask;


import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.os.AsyncTask;

import android.util.Log;
import com.PolygonGamesStudio.UJourney.ContentProvider.CacheContentProvider;
import com.PolygonGamesStudio.UJourney.LogInActivity;
import com.google.android.gms.auth.GoogleAuthException;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.auth.GooglePlayServicesAvailabilityException;
import com.google.android.gms.auth.UserRecoverableAuthException;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GoogleLogin extends AsyncTask<Void, Void, Void> {

    private static final String TAG = "TokenInfoTask";

    private static final String ID_KEY = "id";
    private static final String NAME_KEY = "given_name";
    private static final String LASTNAME_KEY = "family_name";
    private static final String PICTURE_KEY = "picture";
    private final ContentResolver mContentResolver;


    protected LogInActivity mActivity;
    protected String mScope;
    protected String mEmail;
    protected int mRequestCode;

    public GoogleLogin(LogInActivity activity, String email, String scope, int requestCode, ContentResolver mContentResolver) {
        this.mContentResolver = mContentResolver;
        this.mActivity = activity;
        this.mScope = scope;
        this.mEmail = email;
        this.mRequestCode = requestCode;
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            JSONObject profile = fetchNameFromProfileServer();
            createGmailProfile(profile);
            mActivity.startMainActivity();
        } catch (IOException ex) {
            onError("Following Error occured, please try again. " + ex.getMessage(), ex);
        } catch (JSONException e) {
            onError("Bad response: " + e.getMessage(), e);
        }
        return null;
    }

    private void createGmailProfile(JSONObject profile) {
        final String USER_ID = "id";
        final String USER_NAME = "name";
        final String LASTUSER_NAME = "lastname";
        final String USER_PICTURE = "picture";

        ContentValues cv = new ContentValues();

        try {
            cv.put(USER_ID, profile.getString(ID_KEY));
            cv.put(USER_NAME, profile.getString(NAME_KEY));
            cv.put(LASTUSER_NAME, profile.getString(LASTNAME_KEY));
            cv.put(USER_PICTURE, profile.getString(PICTURE_KEY));

            mContentResolver.insert(CacheContentProvider.USER_CONTENT_URI, cv);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    protected void onError(String msg, Exception e) {
        if (e != null) {
            Log.e(TAG, "Exception: ", e);
        }
    }

    /**
     * Get a authentication token if one is not available. If the error is not recoverable then
     * it displays the error message on parent activity.
     */
    protected String fetchToken() throws IOException {
        try {
            return GoogleAuthUtil.getToken(mActivity, mEmail, mScope);
        } catch (GooglePlayServicesAvailabilityException playEx) {
            // GooglePlayServices.apk is either old, disabled, or not present.
        } catch (UserRecoverableAuthException userRecoverableException) {
            // Unable to authenticate, but the user can fix this.
            // Forward the user to the appropriate activity.
            mActivity.startActivityForResult(userRecoverableException.getIntent(), mRequestCode);
        } catch (GoogleAuthException fatalException) {
            onError("Unrecoverable error " + fatalException.getMessage(), fatalException);
        }
        return null;
    }

    /**
     * Contacts the user info server to get the profile of the user and extracts the first name
     * of the user from the profile. In order to authenticate with the user info server the method
     * first fetches an access token from Google Play services.
     * @throws IOException if communication with user info server failed.
     * @throws JSONException if the response from the server could not be parsed.
     */
    private JSONObject fetchNameFromProfileServer() throws IOException, JSONException {
        String token = fetchToken();
        if (token == null) {
            // error has already been handled in fetchToken()
        }
        URL url = new URL("https://www.googleapis.com/oauth2/v1/userinfo?access_token=" + token);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        int sc = con.getResponseCode();
        if (sc == 200) {
            InputStream is = con.getInputStream();
            JSONObject profile = getProfile(readResponse(is));
            is.close();
            return profile;

        } else if (sc == 401) {
            GoogleAuthUtil.invalidateToken(mActivity, token);
            onError("Server auth error, please try again.", null);
            Log.i(TAG, "Server auth error: " + readResponse(con.getErrorStream()));
            return null;
        } else {
            onError("Server returned the following error code: " + sc, null);
            return null;

        }

    }

    /**
     * Reads the response from the input stream and returns it as a string.
     */
    private static String readResponse(InputStream is) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] data = new byte[2048];
        int len = 0;
        while ((len = is.read(data, 0, data.length)) >= 0) {
            bos.write(data, 0, len);
        }
        return new String(bos.toByteArray(), "UTF-8");
    }

    /**
     * Parses the response and returns the first name of the user.
     * @throws JSONException if the response is not JSON or if first name does not exist in response
     */
    private JSONObject getProfile(String jsonResponse) throws JSONException {
        return new JSONObject(jsonResponse);
    }
}
