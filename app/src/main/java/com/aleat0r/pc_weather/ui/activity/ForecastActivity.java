package com.aleat0r.pc_weather.ui.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.aleat0r.pc_weather.App;
import com.aleat0r.pc_weather.R;
import com.aleat0r.pc_weather.mvp.ForecastContract;
import com.aleat0r.pc_weather.pojo.forecast.ForecastWeatherData;
import com.aleat0r.pc_weather.ui.adapter.ForecastAdapter;
import com.aleat0r.pc_weather.util.Constants;
import com.aleat0r.pc_weather.util.Utils;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Aleksandr Kovalenko on 27.10.2017.
 */

public class ForecastActivity extends AppCompatActivity implements ForecastContract.View {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    private RecyclerView mRvForecast;
    private ProgressDialog mProgressDialog;

    @Inject
    public ForecastContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);
        ButterKnife.bind(this);
        App.getComponent().inject(this);
        initViews();

        mPresenter.init(this, getIntent().getDoubleExtra(Constants.WEATHER_LATITUDE_INTENT_EXTRA, 0),
                getIntent().getDoubleExtra(Constants.WEATHER_LONGITUDE_INTENT_EXTRA, 0));
        mPresenter.onCreate();
    }

    private void initViews() {
        initToolbar();
        initRecycler();
        mProgressDialog = Utils.createProgressDialog(this);
    }

    private void initToolbar() {
        mToolbar.setTitle(R.string.empty_place);
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_back);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void initRecycler() {
        mRvForecast = (RecyclerView) findViewById(R.id.rv_forecast);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRvForecast.setLayoutManager(layoutManager);
    }

    @Override
    public void updateInfo(ForecastWeatherData forecastWeatherData) {
        ForecastAdapter forecastAdapter = new ForecastAdapter(this, forecastWeatherData.getWeatherList());
        mRvForecast.setAdapter(forecastAdapter);

        mToolbar.setTitle(forecastWeatherData.getCity().getName());
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
