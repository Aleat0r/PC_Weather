package com.aleat0r.pc_weather.mvp.model;

import com.aleat0r.pc_weather.mvp.ForecastContract;
import com.aleat0r.pc_weather.network.ApiUtils;
import com.aleat0r.pc_weather.network.OpenWeatherApiService;
import com.aleat0r.pc_weather.pojo.forecast.ForecastWeatherData;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Aleksandr Kovalenko on 27.10.2017.
 */

public class ForecastModel implements ForecastContract.Model {

    private OpenWeatherApiService mWebService;

    @Inject
    public ForecastModel(OpenWeatherApiService webService) {
        mWebService = webService;
    }

    @Override
    public void getWeatherForecast(double longitude, double latitude, final OnGetForecastWeatherCallback onGetForecastWeatherCallback) {
        mWebService.loadForecastWeather(ApiUtils.getQueryParams(longitude, latitude)).enqueue(new Callback<ForecastWeatherData>() {

            @Override
            public void onResponse(Call<ForecastWeatherData> call, Response<ForecastWeatherData> response) {
                ForecastWeatherData forecastWeatherData = response.body();
                onGetForecastWeatherCallback.onSuccess(forecastWeatherData);
            }

            @Override
            public void onFailure(Call<ForecastWeatherData> call, Throwable t) {
                onGetForecastWeatherCallback.onFailure(t.getMessage());
            }
        });
    }
}
