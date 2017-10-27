package com.aleat0r.pc_weather.dagger;

import com.aleat0r.pc_weather.ui.activity.ForecastActivity;
import com.aleat0r.pc_weather.ui.activity.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Aleksandr Kovalenko on 26.10.2017.
 */

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    void inject(MainActivity mainActivity);
    void inject(ForecastActivity forecastActivity);

}
