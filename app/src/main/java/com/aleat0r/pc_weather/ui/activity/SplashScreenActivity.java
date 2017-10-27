package com.aleat0r.pc_weather.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.Toast;

import com.aleat0r.pc_weather.R;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

/**
 * Created by Aleksandr Kovalenko on 27.10.2017.
 */

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splah_screen);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Dexter.withActivity(this)
                .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(permissionListener)
                .check();
    }

    PermissionListener permissionListener = new PermissionListener() {
        @Override
        public void onPermissionGranted(PermissionGrantedResponse response) {
            moveToNextActivity();
        }

        @Override
        public void onPermissionDenied(PermissionDeniedResponse response) {
            Toast.makeText(SplashScreenActivity.this, R.string.permission_denied, Toast.LENGTH_LONG).show();
            finish();
        }

        @Override
        public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
            token.continuePermissionRequest();
        }
    };

    private void moveToNextActivity() {
        Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
