package com.aleat0r.pc_weather.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aleat0r.pc_weather.R;
import com.aleat0r.pc_weather.pojo.forecast.WeatherList;
import com.aleat0r.pc_weather.util.Utils;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Aleksandr Kovalenko on 27.10.2017.
 */

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder> {

    private Context mContext;
    private List<WeatherList> mModelsList;

    public ForecastAdapter(Context context, List<WeatherList> modelsList) {
        mContext = context;
        mModelsList = modelsList;
    }

    @Override
    public ForecastViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_card_forecast, parent, false);
        return new ForecastViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ForecastViewHolder holder, int position) {
        WeatherList forecastData = mModelsList.get(position);

        String weatherTypeIconUrl = Utils.getWeatherIconUrl(mContext, forecastData.getWeather().get(0).getIcon());
        Picasso.with(mContext).load(weatherTypeIconUrl).placeholder(R.drawable.ic_place_holder)
                .error(R.drawable.ic_error_loading).into(holder.mImgWeatherType);

        holder.mTvDate.setText(Utils.getForecastDateFromUnix(mContext, forecastData.getDt()));

        holder.mTvWeatherDescription.setText(forecastData.getWeather().get(0).getDescription());
        holder.mTvTemperature.setText(String.valueOf(forecastData.getTemp().getDay() + mContext.getString(R.string.empty_place) + mContext.getString(R.string.degrees_celsius)));
        holder.mTvWind.setText(String.valueOf(forecastData.getSpeed()) + mContext.getString(R.string.empty_place) + mContext.getString(R.string.metric_speed));
        holder.mTvHumidity.setText(String.valueOf(forecastData.getHumidity()) + mContext.getString(R.string.empty_place) + mContext.getString(R.string.percent));
        holder.mTvCloudiness.setText(String.valueOf(forecastData.getClouds()) + mContext.getString(R.string.empty_place) + mContext.getString(R.string.percent));
        holder.mTvMinTemperature.setText(String.valueOf(forecastData.getTemp().getNight() + mContext.getString(R.string.empty_place) + mContext.getString(R.string.degrees_celsius)));
        holder.mTvMaxTemperature.setText(String.valueOf(forecastData.getTemp().getMax() + mContext.getString(R.string.empty_place) + mContext.getString(R.string.degrees_celsius)));
    }

    @Override
    public int getItemCount() {
        return mModelsList.size();
    }

    public class ForecastViewHolder extends RecyclerView.ViewHolder {

        private ImageView mImgWeatherType;
        private TextView mTvDate;
        private TextView mTvWeatherDescription;
        private TextView mTvTemperature;
        private TextView mTvWind;
        private TextView mTvHumidity;
        private TextView mTvCloudiness;
        private TextView mTvMinTemperature;
        private TextView mTvMaxTemperature;

        public ForecastViewHolder(View itemView) {
            super(itemView);
            mImgWeatherType = (ImageView) itemView.findViewById(R.id.img_weather_type);
            mTvDate = (TextView) itemView.findViewById(R.id.tv_date);
            mTvWeatherDescription = (TextView) itemView.findViewById(R.id.tv_weather_description);
            mTvTemperature = (TextView) itemView.findViewById(R.id.tv_temperature);
            mTvWind = (TextView) itemView.findViewById(R.id.tv_wind);
            mTvHumidity = (TextView) itemView.findViewById(R.id.tv_humidity);
            mTvCloudiness = (TextView) itemView.findViewById(R.id.tv_cloudiness);
            mTvMinTemperature = (TextView) itemView.findViewById(R.id.tv_min_temperature);
            mTvMaxTemperature = (TextView) itemView.findViewById(R.id.tv_max_temperature);
        }
    }

}