package com.aleat0r.pc_weather.mvp.presenter;

import com.aleat0r.pc_weather.mvp.MainContract;
import com.aleat0r.pc_weather.pojo.current.CurrentWeatherData;

import javax.inject.Inject;

/**
 * Created by Aleksandr Kovalenko on 26.10.2017.
 */

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View mView;

    private MainContract.Model mModel;

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
            mView.showHideProgressDialog();
            mView.updateInfo(currentWeatherData);
        }

        @Override
        public void onFailure(String message) {
            mView.showHideProgressDialog();
            mView.showMessage(message);
        }
    };
}
