package com.example.a11699.all.mvpstudy.weather_mvp.ui.activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a11699.all.R;
import com.example.a11699.all.mvpstudy.weather_mvp.model.Weather;
import com.example.a11699.all.mvpstudy.weather_mvp.model.WeatherInfo;
import com.example.a11699.all.mvpstudy.weather_mvp.presenter.WeatherPresenter;
import com.example.a11699.all.mvpstudy.weather_mvp.presenter.impl.WeatherPresenterImpl;
import com.example.a11699.all.mvpstudy.weather_mvp.ui.dao.WeatherView_dao;

public class WeatherActivity extends AppCompatActivity implements WeatherView_dao, View.OnClickListener  {
    private Dialog loadingDialog;
    private EditText cityNOInput;
    private TextView city;
    private TextView cityNO;
    private TextView temp;
    private TextView wd;
    private TextView ws;
    private TextView sd;
    private TextView wse;
    private TextView time;
    private TextView njd;

    private WeatherPresenter weatherPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        init();
    }
    private void init() {
        cityNOInput = findViewById(R.id.et_city_no);
        city = findViewById(R.id.tv_city);
        cityNO = findViewById(R.id.tv_city_no);
        temp = findViewById(R.id.tv_temp);
        wd = findViewById(R.id.tv_WD);
        ws = findViewById(R.id.tv_WS);
        sd = findViewById(R.id.tv_SD);
        wse = findViewById(R.id.tv_WSE);
        time = findViewById(R.id.tv_time);
        njd = findViewById(R.id.tv_njd);

        findViewById(R.id.btn_go).setOnClickListener(this);

        weatherPresenter = new WeatherPresenterImpl(this); //传入WeatherView
        loadingDialog = new ProgressDialog(this);
        loadingDialog.setTitle("加载天气中...");
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_go:
                weatherPresenter.getWeather(cityNOInput.getText().toString().trim());
                break;
        }
    }

    @Override
    public void showLoading() {
        loadingDialog.show();
    }

    @Override
    public void hideLoading() {
        loadingDialog.dismiss();
    }

    @Override
    public void showError() {
        Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setWeatherinfo(Weather weather) {
        WeatherInfo info = weather.getWeatherInfo();
        city.setText(info.getCity());
        cityNO.setText(info.getCityid());
        temp.setText(info.getTemp());
        wd.setText(info.getWD());
        ws.setText(info.getWS());
        sd.setText(info.getSD());
        wse.setText(info.getWS());
        time.setText(info.getTemp());
        njd.setText(info.getNjd());
    }
}
