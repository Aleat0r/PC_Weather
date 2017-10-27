package com.aleat0r.pc_weather.mvp.presenter;

import android.Manifest;
import android.content.Intent;

import com.aleat0r.pc_weather.R;
import com.aleat0r.pc_weather.mvp.SplashContract;
import com.aleat0r.pc_weather.ui.activity.BaseActivity;
import com.aleat0r.pc_weather.ui.activity.MainActivity;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import javax.inject.Inject;

/**
 * Created by Aleksandr Kovalenko on 27.10.2017.
 */

public class SplashPresenter implements SplashContract.Presenter {

    private BaseActivity mActivity;

    @Inject
    public SplashPresenter() {
    }

    @Override
    public void init(BaseActivity activity) {
        mActivity = activity;
    }

    @Override
    public void onCreate() {
        Dexter.withActivity(mActivity)
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
            mActivity.showMessage(mActivity.getString(R.string.permission_denied));
            mActivity.finish();
        }

        @Override
        public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
            token.continuePermissionRequest();
        }
    };

    private void moveToNextActivity() {
        Intent intent = new Intent(mActivity, MainActivity.class);
        mActivity.startActivity(intent);
        mActivity.finish();
    }
}
