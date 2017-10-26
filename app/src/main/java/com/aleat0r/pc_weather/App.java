package com.aleat0r.pc_weather;

import android.app.Application;

import com.aleat0r.pc_weather.dagger.AppComponent;
import com.aleat0r.pc_weather.dagger.AppModule;
import com.aleat0r.pc_weather.dagger.DaggerAppComponent;

/**
 * Created by Aleksandr Kovalenko on 26.10.2017.
 */

public class App extends Application {

    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public static AppComponent getComponent() {
        return appComponent;
    }
}