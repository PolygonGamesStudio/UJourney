package com.PolygonGamesStudio.UJourney;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import com.PolygonGamesStudio.UJourney.AsyncTask.GoogleLogin;
import com.PolygonGamesStudio.UJourney.Service.CategoryService;
import com.PolygonGamesStudio.UJourney.Service.HistoryService;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.squareup.picasso.Picasso;

public class LogInActivity extends Activity {

    public static final String EXTRA_ACCOUNTNAME = "extra_accountname";
    private static final String SCOPE = "oauth2:https://www.googleapis.com/auth/userinfo.profile";
    static final int REQUEST_CODE_RECOVER_FROM_AUTH_ERROR = 1001;
    private AccountManager mAccountManager;
    private String mEmail;
    private Spinner mAccountTypesSpinner;
    private String[] mNamesArray;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        Button anonymousButton = (Button) findViewById(R.id.anonymousButton);
        anonymousButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(LogInActivity.this, MainMenuActivity.class);
                startActivity(intent);
            }
        });

        Button loginButton = (Button) findViewById(R.id.LoginButton);
        loginButton.setOnClickListener(new  View.OnClickListener() {
            public void onClick(View v) {
            }
        });

        final Button signGoogleButton = (Button) findViewById(R.id.signGoogle);
        signGoogleButton.setOnClickListener(new  View.OnClickListener() {
            public void onClick(View v) {
                mNamesArray = getAccountNames();
                signGoogleButton.setEnabled(false);
                GoogleLogin task = new GoogleLogin(LogInActivity.this, mNamesArray[0], SCOPE, REQUEST_CODE_RECOVER_FROM_AUTH_ERROR, getContentResolver());
                task.execute();
            }
        });
    }

    private String[] getAccountNames() {
        mAccountManager = AccountManager.get(this);
        Account[] accounts = mAccountManager.getAccountsByType(GoogleAuthUtil.GOOGLE_ACCOUNT_TYPE);
        String[] names = new String[accounts.length];
        for (int i = 0; i < names.length; i++) {
            names[i] = accounts[i].name;
        }
        return names;
    }

    public void startMainActivity() {
        Intent intent = new Intent(LogInActivity.this, MainMenuActivity.class);
        startActivity(intent);
    }
}
