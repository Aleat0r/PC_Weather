package com.aleat0r.pc_weather.util;

import android.app.ProgressDialog;
import android.content.Context;

import com.aleat0r.pc_weather.R;
import com.aleat0r.pc_weather.network.ApiConstants;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Aleksandr Kovalenko on 26.10.2017.
 */

public class Utils {

    public static ProgressDialog createProgressDialog(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(context.getResources().getString(R.string.progress_dialog_message));
        return progressDialog;
    }

    public static String getWeatherIconUrl(Context context, String iconName){
        String weatherTypeIconUrl = ApiConstants.ICON_WEATHER_TYPE_URL + iconName + context.getString(R.string.image_format);
        return weatherTypeIconUrl;
    }

    private static String getDateFromMillis(Context context, int format, long millis) {
        String date = new SimpleDateFormat(context.getString(format), Locale.ENGLISH).format(new Date(millis));
        return date;
    }

    public static String getForecastDateFromUnix(Context context, long unixDate) {
        String date = getDateFromMillis(context, R.string.forecast_date_format, unixDate * 1000L);
        return date;
    }

}
