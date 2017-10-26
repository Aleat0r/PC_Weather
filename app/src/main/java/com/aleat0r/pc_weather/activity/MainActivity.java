package com.aleat0r.pc_weather.activity;

import android.app.ProgressDialog;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aleat0r.pc_weather.App;
import com.aleat0r.pc_weather.R;
import com.aleat0r.pc_weather.mvp.MainContract;
import com.aleat0r.pc_weather.pojo.current.CurrentWeatherData;
import com.aleat0r.pc_weather.util.Utils;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tv_city)
    TextView mTvCity;
    @BindView(R.id.tv_location)
    TextView mTvLocation;
    @BindView(R.id.tv_temperature)
    TextView mTvTemperature;
    @BindView(R.id.tv_min_temperature)
    TextView mTvMinTemperature;
    @BindView(R.id.tv_max_temperature)
    TextView mTvMaxTemperature;
    @BindView(R.id.tv_weather_description)
    TextView mTvWeatherDescription;
    @BindView(R.id.img_weather_type)
    ImageView mImgWeatherType;

    private ProgressDialog mProgressDialog;

    @Inject
    public MainContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        App.getComponent().inject(this);
        initViews();
        mPresenter.init(this);
        mPresenter.onCreate();
    }

    private void initViews() {
        initToolbar();
        mProgressDialog = Utils.createProgressDialog(this);
    }

    private void initToolbar() {
        mToolbar.setTitle(R.string.weather_label);
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_back);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void updateInfo(CurrentWeatherData weatherData) {
        mTvCity.setText(weatherData.getName());
        mTvLocation.setText(weatherData.getCoord().getLon() + ", " + weatherData.getCoord().getLat());
        mTvTemperature.setText(Double.toString(weatherData.getMain().getTemp()));
        mTvMinTemperature.setText(Double.toString(weatherData.getMain().getTempMin()));
        mTvMaxTemperature.setText(Double.toString(weatherData.getMain().getTempMax()));
        mTvWeatherDescription.setText(weatherData.getWeather().get(0).getDescription());

        Picasso.with(MainActivity.this).load(Utils.getWeatherIconUrl(MainActivity.this, weatherData.getWeather().get(0).getIcon()))
                .placeholder(R.drawable.ic_place_holder)
                .error(R.drawable.ic_error_loading).into(mImgWeatherType);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showHideProgressDialog() {
        if (mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        } else {
            mProgressDialog.show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
