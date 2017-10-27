package com.aleat0r.pc_weather.mvp;

import com.aleat0r.pc_weather.pojo.forecast.ForecastWeatherData;
import com.aleat0r.pc_weather.pojo.forecast.WeatherList;

import java.util.List;

/**
 * Created by Aleksandr Kovalenko on 27.10.2017.
 */

public interface ForecastContract {

    interface Presenter {

        void onCreate();

        void init(ForecastContract.View view, double latitude, double longitude);
    }

    interface View {

        void updateInfo(ForecastWeatherData forecastWeatherData);

        void showMessage(String message);

        void showHideProgressDialog();
    }

    interface Model {

        void getWeatherForecast(double longitude, double latitude, OnGetForecastWeatherCallback onGetForecastWeatherCallback);

        interface OnGetForecastWeatherCallback {

            void onSuccess(ForecastWeatherData forecastWeatherData);

            void onFailure(String message);
        }
    }

}
