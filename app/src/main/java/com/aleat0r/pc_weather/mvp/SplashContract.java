package com.aleat0r.pc_weather.mvp;

import com.aleat0r.pc_weather.ui.activity.BaseActivity;

/**
 * Created by Aleksandr Kovalenko on 27.10.2017.
 */

public interface SplashContract {

    interface Presenter {

        void onCreate();

        void init(BaseActivity activity);

    }

}
