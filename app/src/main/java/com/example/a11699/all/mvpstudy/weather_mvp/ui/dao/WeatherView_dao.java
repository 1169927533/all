package com.example.a11699.all.mvpstudy.weather_mvp.ui.dao;

import com.example.a11699.all.mvpstudy.weather_mvp.model.Weather;

/**
 * 作者：余智强
 * 2019/3/17
 */
public interface WeatherView_dao {
    void showLoading();
    void hideLoading();
    void showError();
    void setWeatherinfo(Weather weather);
}
