package com.aleat0r.pc_weather.ui.activity;

import android.os.Bundle;
import android.view.WindowManager;

import com.aleat0r.pc_weather.App;
import com.aleat0r.pc_weather.R;
import com.aleat0r.pc_weather.mvp.SplashContract;

import javax.inject.Inject;

/**
 * Created by Aleksandr Kovalenko on 27.10.2017.
 */

public class SplashScreenActivity extends BaseActivity {

    @Inject
    public SplashContract.Presenter mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splah_screen);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        App.getComponent().inject(this);

        mPresenter.init(this);
        mPresenter.onCreate();
    }

}
