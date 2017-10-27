package com.aleat0r.pc_weather.mvp.presenter;

import android.content.Context;
import android.content.Intent;

import com.aleat0r.pc_weather.mvp.MainContract;
import com.aleat0r.pc_weather.pojo.Coord;
import com.aleat0r.pc_weather.pojo.current.CurrentWeatherData;
import com.aleat0r.pc_weather.ui.activity.ForecastActivity;
import com.aleat0r.pc_weather.util.Constants;

import javax.inject.Inject;

/**
 * Created by Aleksandr Kovalenko on 26.10.2017.
 */

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View mView;
    private MainContract.Model mModel;

    private Coord mLocation;

    @Inject
    public MainPresenter(MainContract.Model model) {
        mModel = model;
    }

    @Override
    public void init(MainContract.View view) {
        mView = view;
    }

    @Override
    public void onCreate() {
        mView.showHideProgressDialog();
        mModel.getUserLocationWeather(onGetWeatherCallback);
    }

    @Override
    public void locationChanged(double longitude, double latitude) {
        mView.showHideProgressDialog();
        mModel.getCurrentWeather(longitude, latitude, onGetWeatherCallback);
    }

    MainContract.Model.OnGetWeatherCallback onGetWeatherCallback = new MainContract.Model.OnGetWeatherCallback() {
        @Override
        public void onSuccess(CurrentWeatherData currentWeatherData) {
            mLocation = currentWeatherData.getCoord();
            mView.showHideProgressDialog();
            mView.updateInfo(currentWeatherData);
        }

        @Override
        public void onFailure(String message) {
            mView.showHideProgressDialog();
            mView.showMessage(message);
        }
    };

    @Override
    public void openForecast() {
        if (mLocation != null) {
            Context context = (Context) mView;
            Intent intent = new Intent(context, ForecastActivity.class);
            intent.putExtra(Constants.WEATHER_LATITUDE_INTENT_EXTRA, mLocation.getLat());
            intent.putExtra(Constants.WEATHER_LONGITUDE_INTENT_EXTRA, mLocation.getLon());
            context.startActivity(intent);
        }
    }
}
