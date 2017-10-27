package com.aleat0r.pc_weather.mvp.presenter;

import com.aleat0r.pc_weather.mvp.ForecastContract;
import com.aleat0r.pc_weather.pojo.forecast.ForecastWeatherData;

import javax.inject.Inject;

/**
 * Created by Aleksandr Kovalenko on 27.10.2017.
 */

public class ForecastPresenter implements ForecastContract.Presenter {

    private ForecastContract.Model mModel;
    private ForecastContract.View mView;

    private double mLatitude, mLongitude;

    @Inject
    public ForecastPresenter(ForecastContract.Model model) {
        mModel = model;
    }

    @Override
    public void init(ForecastContract.View view, double latitude, double longitude) {
        mView = view;
        mLatitude = latitude;
        mLongitude = longitude;
    }

    @Override
    public void onCreate() {
        mView.showHideProgressDialog();
        mModel.getWeatherForecast(mLongitude, mLatitude, onGetForecastWeatherCallback);
    }

    ForecastContract.Model.OnGetForecastWeatherCallback onGetForecastWeatherCallback = new ForecastContract.Model.OnGetForecastWeatherCallback() {
        @Override
        public void onSuccess(ForecastWeatherData forecastWeatherData) {
            mView.showHideProgressDialog();
            mView.updateInfo(forecastWeatherData);
        }

        @Override
        public void onFailure(String message) {
            mView.showHideProgressDialog();
            mView.showMessage(message);
        }
    };
}
