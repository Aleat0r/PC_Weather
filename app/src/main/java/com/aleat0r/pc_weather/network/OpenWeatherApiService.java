package com.aleat0r.pc_weather.network;


import com.aleat0r.pc_weather.pojo.current.CurrentWeatherData;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Created by Aleksandr Kovalenko on 26.10.2017.
 */

public interface OpenWeatherApiService {

    @POST("weather")
    Call<CurrentWeatherData> loadCurrentWeather(@QueryMap Map<String, Object> params);

}
