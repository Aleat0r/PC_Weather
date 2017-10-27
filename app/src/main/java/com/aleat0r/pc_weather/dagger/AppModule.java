package com.aleat0r.pc_weather.dagger;

import android.content.Context;

import com.aleat0r.pc_weather.mvp.ForecastContract;
import com.aleat0r.pc_weather.mvp.MainContract;
import com.aleat0r.pc_weather.mvp.model.ForecastModel;
import com.aleat0r.pc_weather.mvp.model.MainModel;
import com.aleat0r.pc_weather.mvp.presenter.ForecastPresenter;
import com.aleat0r.pc_weather.mvp.presenter.MainPresenter;
import com.aleat0r.pc_weather.network.ApiConstants;
import com.aleat0r.pc_weather.network.OpenWeatherApiService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Aleksandr Kovalenko on 26.10.2017.
 */

@Module
public class AppModule {

    private Context mContext;

    public AppModule(Context context) {
        mContext = context;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return mContext;
    }

    @Provides
    @Singleton
    OpenWeatherApiService provideOpenWeatherApiService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiConstants.BASE_URL)
                .client(getLoggingInterceptor())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(OpenWeatherApiService.class);
    }

    private OkHttpClient getLoggingInterceptor() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder().addInterceptor(logging).build();
    }

    @Provides
    MainContract.Model provideMainModel(Context context, OpenWeatherApiService openWeatherApiService) {
        return new MainModel(context, openWeatherApiService);
    }

    @Provides
    MainContract.Presenter provideMainPresenter(MainContract.Model model) {
        return new MainPresenter(model);
    }

    @Provides
    ForecastContract.Model provideForecastModel(OpenWeatherApiService openWeatherApiService) {
        return new ForecastModel(openWeatherApiService);
    }

    @Provides
    ForecastContract.Presenter provideForecastPresenter(ForecastContract.Model model) {
        return new ForecastPresenter(model);
    }

}