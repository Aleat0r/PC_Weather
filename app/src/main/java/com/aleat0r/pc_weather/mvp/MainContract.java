package com.aleat0r.pc_weather.mvp;

import com.aleat0r.pc_weather.pojo.current.CurrentWeatherData;

/**
 * Created by Aleksandr Kovalenko on 26.10.2017.
 */

public interface MainContract {

    interface Presenter {

        void onCreate();

        void init(MainContract.View view);

        void locationChanged(double longitude, double latitude);

    }

    interface View {

        void updateInfo(CurrentWeatherData currentWeatherData);

        void showMessage(String message);

        void showHideProgressDialog();
    }

    interface Model {

        void getUserLocationWeather(OnGetWeatherCallback onGetWeatherCallback);

        void getCurrentWeather(double longitude, double latitude, OnGetWeatherCallback onGetWeatherCallback);

        interface OnGetWeatherCallback {

            void onSuccess(CurrentWeatherData currentWeatherData);

            void onFailure(String message);
        }
    }

}
