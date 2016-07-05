package com.example.jdevani.fcmdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.firebase.iid.FirebaseInstanceId;


public class MainActivity extends AppCompatActivity {

    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //here we have to check whether the device on which
        //app is running had the Play services available or not.
        if(checkPlayServices())
        {
            printMyFCMToken();
        }

        //If you click on button your FCM will be printed in Log
        Button btnPrintFCMToken=(Button)findViewById(R.id.btnPrintFCMToken);
        btnPrintFCMToken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                printMyFCMToken();
            }
        });
    }

    private void printMyFCMToken()
    {
        Log.e("FCM Token=",""+ FirebaseInstanceId.getInstance().getToken());
    }

    private boolean checkPlayServices() {
        GoogleApiAvailability googleAPI = GoogleApiAvailability.getInstance();
        int result = googleAPI.isGooglePlayServicesAvailable(this);
        if(result != ConnectionResult.SUCCESS) {
            if(googleAPI.isUserResolvableError(result)) {
                googleAPI.getErrorDialog(this, result,
                        PLAY_SERVICES_RESOLUTION_REQUEST).show();
            }
            return false;
        }

        return true;
    }
}