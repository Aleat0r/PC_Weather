package com.aleat0r.pc_weather.mvp.model;

import android.content.Context;
import android.location.Location;

import com.aleat0r.pc_weather.R;
import com.aleat0r.pc_weather.mvp.MainContract;
import com.aleat0r.pc_weather.network.ApiConstants;
import com.aleat0r.pc_weather.network.OpenWeatherApiService;
import com.aleat0r.pc_weather.pojo.current.CurrentWeatherData;
import com.aleat0r.pc_weather.util.SingleShotLocationProvider;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Aleksandr Kovalenko on 26.10.2017.
 */

public class MainModel implements MainContract.Model {

    private Context mContext;
    private OpenWeatherApiService mWebService;

    @Inject
    public MainModel(Context context, OpenWeatherApiService webService) {
        mContext = context;
        mWebService = webService;
    }

    @Override
    public void getUserLocationWeather(final OnGetWeatherCallback onGetWeatherCallback) {
        SingleShotLocationProvider.requestSingleUpdate(mContext, new SingleShotLocationProvider.LocationCallback() {
            @Override
            public void onNewLocationAvailable(Location location) {
                if (location != null) {
                    getCurrentWeather(location.getLongitude(), location.getLatitude(), onGetWeatherCallback);
                } else {
                    onGetWeatherCallback.onFailure(mContext.getString(R.string.error_find_location));
                }
            }
        });
    }

    @Override
    public void getCurrentWeather(double longitude, double latitude, final OnGetWeatherCallback onGetWeatherCallback) {
        mWebService.loadCurrentWeather(getQueryParams(longitude, latitude)).enqueue(new Callback<CurrentWeatherData>() {

            @Override
            public void onResponse(Call<CurrentWeatherData> call, Response<CurrentWeatherData> response) {
                CurrentWeatherData weatherData = response.body();
                onGetWeatherCallback.onSuccess(weatherData);
            }

            @Override
            public void onFailure(Call<CurrentWeatherData> call, Throwable t) {
                onGetWeatherCallback.onFailure(t.getMessage());
            }
        });
    }

    private Map getQueryParams(double longitude, double latitude) {
        Map params = getGeneralQueryParams();
        params.put(ApiConstants.QUERY_NAME_LONGITUDE, longitude);
        params.put(ApiConstants.QUERY_NAME_LATITUDE, latitude);
        return params;
    }

    private Map getGeneralQueryParams() {
        Map<String, Object> params = new HashMap<>();
        params.put(ApiConstants.QUERY_NAME_APP_ID, ApiConstants.API_KEY);
        params.put(ApiConstants.QUERY_NAME_UNITS_OF_MEASURE, ApiConstants.METRIC_UNITS_OF_MEASURE);
        return params;
    }

}